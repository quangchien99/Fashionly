package chn.phm.presentation.screens.fashionly

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.usecase.fashionly.TestApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FashionlyViewModel @Inject constructor(
    private val testApiUseCase: TestApiUseCase
) : ViewModel() {

    fun testApi() {
        viewModelScope.launch {
            val testAPIResult = testApiUseCase.execute()
            Log.d("Chien", "FashionlyViewModel: testAPIResult= $testAPIResult")
        }
    }
}
