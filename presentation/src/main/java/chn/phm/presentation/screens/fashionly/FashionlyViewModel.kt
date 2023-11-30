package chn.phm.presentation.screens.fashionly

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.usecase.fashionly.TestApiUseCase
import chn.phm.domain.usecase.fashionly.UploadImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FashionlyViewModel @Inject constructor(
    private val testApiUseCase: TestApiUseCase,
    private val uploadImagesUseCase: UploadImagesUseCase,
) : ViewModel() {

    fun testApi() {
        viewModelScope.launch {
            val testAPIResult = testApiUseCase.execute()
            Log.d("Chien", "FashionlyViewModel: testAPIResult= $testAPIResult")
        }
    }

    fun uploadImages(uris: List<Uri>) {
        viewModelScope.launch {
            Log.d("Chien", "uploading Images")
            val result = uploadImagesUseCase.execute(uris)
            if (result.isSuccess) {
                result.getOrNull()?.forEach { url ->
                    Log.d("Chien", "uploadImages successfully: $url")
                }
            } else {
                Log.d("Chien", "uploadImages failed: ${result.exceptionOrNull()}")
            }
        }
    }
}
