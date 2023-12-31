package chn.phm.presentation.screens.fashionly

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.model.fashionly.FashionlyData
import chn.phm.domain.model.fashionly.FashionlyResultDomain
import chn.phm.domain.usecase.fashionly.FashionizeUseCase
import chn.phm.domain.usecase.fashionly.InsertFashionlyResultUseCase
import chn.phm.domain.usecase.fashionly.SaveImageUseCase
import chn.phm.domain.usecase.fashionly.UploadImagesUseCase
import chn.phm.domain.usecase.remoteconfig.GetConfigValueUseCase
import chn.phm.domain.usecase.setting.GetSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FashionlyViewModel @Inject constructor(
    private val uploadImagesUseCase: UploadImagesUseCase,
    private val fashionizeUseCase: FashionizeUseCase,
    private val getConfigValueUseCase: GetConfigValueUseCase,
    private val saveImageUseCase: SaveImageUseCase,
    private val insertFashionlyResultUseCase: InsertFashionlyResultUseCase,
    private val getSettingUseCase: GetSettingUseCase
) : ViewModel() {

    private val _uploadedImages: MutableLiveData<List<String>> = MutableLiveData(emptyList())
    private val _stableDiffusionApiKey: MutableLiveData<String> = MutableLiveData("")

    private val _fashionlyUiState: MutableLiveData<FashionlyUiState> = MutableLiveData()
    val fashionlyUiState: LiveData<FashionlyUiState> = _fashionlyUiState

    private val _clothType: MutableLiveData<ClothType> = MutableLiveData(ClothType.UPPER_BODY)

    private val _prompt: MutableLiveData<String> = MutableLiveData("")

    private val _uploadImagesStatus: MutableLiveData<Boolean> = MutableLiveData()
    val uploadImagesStatus: LiveData<Boolean> = _uploadImagesStatus

    private val _getAPIKeyStatus: MutableLiveData<Boolean> = MutableLiveData()
    val getAPIKeyStatus: LiveData<Boolean> = _getAPIKeyStatus

    private val currentUris: MutableList<Uri> = mutableListOf()

    fun uploadImages(uris: List<Uri>) {
        Log.d("Fashionly", "uploading Images")
        currentUris.clear()
        currentUris.addAll(uris)
        viewModelScope.launch {
            _fashionlyUiState.value = FashionlyUiState.Loading
            val result = uploadImagesUseCase.execute(uris)
            if (result.isSuccess) {
                Log.d("Fashionly", "uploading Images done")
                result.getOrNull()?.let {
                    _uploadedImages.value = result.getOrNull()
                }
                _uploadImagesStatus.value = true
            } else {
                Log.d("Fashionly", "uploading error")
                result.exceptionOrNull()?.let { exception ->
                    _fashionlyUiState.value = FashionlyUiState.Error(exception)
                }
            }
        }
    }

    fun fashionize() {
        if (_stableDiffusionApiKey.value.isNullOrEmpty() || _uploadedImages.value?.size != 2) {
            _fashionlyUiState.value = FashionlyUiState.Error(Throwable("Invalid Data"))
        } else {
            viewModelScope.launch {
                val latestSettings = getSettingUseCase.execute().firstOrNull()
                latestSettings?.let { settings ->
                    val fashionlyData = FashionlyData(
                        key = _stableDiffusionApiKey.value!!,
                        prompt = _prompt.value
                            ?: "A realistic photo of a model wearing a beautiful white top.",
                        negativePrompt = settings.fashionlySettings.negativePrompt,
//                    modelImage = "https://www.vstar.in/media/cache/350x0/catalog/product/f/0/f09632_parent_1_1653003388.jpg",
                        modelImage = _uploadedImages.value?.first()
                            ?: "https://www.vstar.in/media/cache/350x0/catalog/product/f/0/f09632_parent_1_1653003388.jpg",
//                    clothImage = "https://thumbs.dreamstime.com/b/plain-hollow-female-tank-top-shirt-isolated-white-background-30020169.jpg",
                        clothImage = _uploadedImages.value?.get(1)
                            ?: "https://thumbs.dreamstime.com/b/plain-hollow-female-tank-top-shirt-isolated-white-background-30020169.jpg",
                        clothType = _clothType.value?.value ?: "upper_body",
                        height = settings.fashionlySettings.height,
                        width = settings.fashionlySettings.width,
                        guidanceScale = settings.fashionlySettings.guidanceScale,
                        numInferenceSteps = settings.fashionlySettings.guidanceScale.toInt(),
                        seed = settings.fashionlySettings.seed.toLong(),
                        temp = "no",
                        webhook = null,
                        trackId = null
                    )
                    Log.d("Fashionly", "fashionizing: $fashionlyData")
                    val result = fashionizeUseCase.execute(fashionlyData)
                    if (result.isSuccess) {
                        result.getOrNull()?.let { fashionlyResult ->
                            Log.d("Chien", "fashionize successfully: $fashionlyResult")
                            _fashionlyUiState.value =
                                FashionlyUiState.Success(fashionlyResult.output.first())
                        }
                    } else {
                        Log.d("Chien", "fashionize failed: ${result.exceptionOrNull()}")
                        result.exceptionOrNull()?.let { exception ->
//                        _fashionlyUiState.value = FashionlyUiState.Error(exception)
                            // testing purpose
                            _fashionlyUiState.value =
                                FashionlyUiState.Success("https://thumbs.dreamstime.com/b/plain-hollow-female-tank-top-shirt-isolated-white-background-30020169.jpg")
                        }
                    }
                }
            }
        }
    }

    fun getStableDiffusionApiKey() {
        Log.d("Fashionly", "getStableDiffusionApiKey")
        viewModelScope.launch {
            val apiKey = getConfigValueUseCase.execute("stable_diffusion_api_key")
            _stableDiffusionApiKey.value = apiKey
            if (apiKey.isNotEmpty()) {
                _getAPIKeyStatus.value = true
            }
        }
    }

    fun setClothType(clothType: ClothType) {
        _clothType.value = clothType
    }

    fun setPrompt(prompt: String) {
        _prompt.value = prompt
    }

    fun resetStatus() {
        _getAPIKeyStatus.value = false
        _uploadImagesStatus.value = false
        _fashionlyUiState.value = FashionlyUiState.IDLE
    }

    fun saveImageToStorage(imageUrl: String) {
        Log.d("Fashionly", "saveImageToStorage: $imageUrl")
        viewModelScope.launch {
            val result = saveImageUseCase.execute(imageUrl)
            if (result.isSuccess && result.getOrNull() == true) {
                Log.d("Fashionly", "saveImageToStorage Success")
            } else {
                Log.d("Fashionly", "saveImageToStorage failed")
            }
        }
    }

    fun insertFashionlyResult(resultUrl: String) {
        viewModelScope.launch {
            try {
                val result = insertFashionlyResultUseCase.execute(
                    FashionlyResultDomain(
                        id = -1,
                        timeCreated = System.currentTimeMillis(),
                        modelImageUri = currentUris.first().toString(),
                        clothImageUri = currentUris[1].toString(),
                        resultUrl = resultUrl,
                        prompt = _prompt.value ?: "",
                        clothType = _clothType.value?.value ?: ""
                    )
                )
                Log.e("Fashionly", "Insert fashionly result success id= ${result.getOrNull()}")
            } catch (e: Exception) {
                Log.e("Fashionly", "Insert fashionly result failed")
            }
        }
    }
}

sealed interface FashionlyUiState {
    data class Success(val data: Any? = null) : FashionlyUiState
    data class Error(val error: Throwable) : FashionlyUiState
    object Loading : FashionlyUiState
    object IDLE : FashionlyUiState
}
