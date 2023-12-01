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

    fun uploadImages(uris: List<Uri>) {
        viewModelScope.launch {
            Log.d("Chien", "uploading Images")
            val result = uploadImagesUseCase.execute(uris)
            if (result.isSuccess) {
                result.getOrNull()?.let { urls ->
                    urls.forEach { url ->
                        Log.d("Chien", "uploadImages successfully: $url")
                    }
                    _uploadedImages.value = result.getOrNull()
                }
            } else {
                Log.d("Chien", "uploadImages failed: ${result.exceptionOrNull()}")
            }
        }
    }

    fun fashionize(fashionlyData: FashionlyData) {
        viewModelScope.launch {
            val result = fashionizeUseCase.execute(fashionlyData)
            if (result.isSuccess) {
                result.getOrNull()?.let { fashionlyResult ->
                    Log.d("Chien", "fashionize successfully: $fashionlyResult")
                }
            } else {
                Log.d("Chien", "fashionize failed: ${result.exceptionOrNull()}")
            }
        }
    }
}
