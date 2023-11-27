package chn.phm.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.model.setting.SettingData
import chn.phm.domain.usecase.setting.GetSettingUseCase
import chn.phm.domain.usecase.setting.SaveSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getSettingUseCase: GetSettingUseCase,
    private val saveSettingUseCase: SaveSettingUseCase
) : ViewModel() {

    private val _settingData = MutableStateFlow(SettingData())

    init {
        viewModelScope.launch {
            getSettingUseCase.execute().collect { settings ->
                _settingData.value = settings
            }
        }
    }

    fun markFirstTimeOpened() {
        viewModelScope.launch {
            saveSettingUseCase.execute(_settingData.value.copy(isFirstOpen = false))
        }
    }
}
