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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FashionlyViewModel @Inject constructor(
    private val uploadImagesUseCase: UploadImagesUseCase,
    private val fashionizeUseCase: FashionizeUseCase
) : ViewModel() {

    private val _uploadedImages: MutableLiveData<List<String>> = MutableLiveData()
    val uploadedImages: LiveData<List<String>> = _uploadedImages

    private val _fashionlyUiState: MutableLiveData<FashionlyUiState> = MutableLiveData()
    val fashionlyUiState: LiveData<FashionlyUiState> = _fashionlyUiState

    fun uploadImages(uris: List<Uri>) {
        viewModelScope.launch {
            _fashionlyUiState.value = FashionlyUiState.Loading
            val result = uploadImagesUseCase.execute(uris)
            if (result.isSuccess) {
                result.getOrNull()?.let { urls ->
                    _uploadedImages.value = result.getOrNull()
                }
            } else {
                result.exceptionOrNull()?.let { exception ->
                    _fashionlyUiState.value = FashionlyUiState.Error(exception)
                }
            }
        }
    }

    fun fashionize(fashionlyData: FashionlyData) {
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

sealed interface FashionlyUiState {
    data class Success(val data: Any? = null) : FashionlyUiState
    data class Error(val error: Throwable) : FashionlyUiState
    object Loading : FashionlyUiState
}
