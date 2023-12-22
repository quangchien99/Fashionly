package chn.phm.presentation.screens.fashionly

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.model.fashionly.FashionlyData
import chn.phm.domain.usecase.fashionly.FashionizeUseCase
import chn.phm.domain.usecase.fashionly.UploadImagesUseCase
import chn.phm.domain.usecase.remoteconfig.GetConfigValueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FashionlyViewModel @Inject constructor(
    private val uploadImagesUseCase: UploadImagesUseCase,
    private val fashionizeUseCase: FashionizeUseCase,
    private val getConfigValueUseCase: GetConfigValueUseCase
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

    fun uploadImages(uris: List<Uri>) {
        Log.d("Fashionly", "uploading Images")
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
        Log.d("Fashionly", "fashionizing")
        if (_stableDiffusionApiKey.value.isNullOrEmpty() || _uploadedImages.value?.size != 2) {
            _fashionlyUiState.value = FashionlyUiState.Error(Throwable("Invalid Data"))
        } else {
            val fashionlyData = FashionlyData(
                key = _stableDiffusionApiKey.value!!,
                prompt = _prompt.value
                    ?: "A realistic photo of a model wearing a beautiful white top.",
                negativePrompt = "Low quality, unrealistic, bad cloth, warped cloth",
//                    modelImage = "https://www.vstar.in/media/cache/350x0/catalog/product/f/0/f09632_parent_1_1653003388.jpg",
                modelImage = _uploadedImages.value?.first()
                    ?: "https://www.vstar.in/media/cache/350x0/catalog/product/f/0/f09632_parent_1_1653003388.jpg",
//                    clothImage = "https://thumbs.dreamstime.com/b/plain-hollow-female-tank-top-shirt-isolated-white-background-30020169.jpg",
                clothImage = _uploadedImages.value?.get(1)
                    ?: "https://thumbs.dreamstime.com/b/plain-hollow-female-tank-top-shirt-isolated-white-background-30020169.jpg",
                clothType = _clothType.value?.value ?: "upper_body",
                height = 512,
                width = 384,
                guidanceScale = 8.0,
                numInferenceSteps = 20,
                seed = 128915590,
                temp = "no",
                webhook = null,
                trackId = null
            )

            viewModelScope.launch {
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
                        _fashionlyUiState.value = FashionlyUiState.Error(exception)
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
            if (!apiKey.isEmpty()) {
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
}

sealed interface FashionlyUiState {
    data class Success(val data: Any? = null) : FashionlyUiState
    data class Error(val error: Throwable) : FashionlyUiState
    object Loading : FashionlyUiState
}
