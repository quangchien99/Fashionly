package chn.phm.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.usecase.setting.UpdateIsFirstOpenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val updateIsFirstOpenUseCase: UpdateIsFirstOpenUseCase
) : ViewModel() {

    fun markFirstTimeOpened() {
        viewModelScope.launch {
            updateIsFirstOpenUseCase.execute()
        }
    }
}
