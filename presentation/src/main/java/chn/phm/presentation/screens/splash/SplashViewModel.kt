package chn.phm.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.model.setting.SettingData
import chn.phm.domain.usecase.setting.GetSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSettingUseCase: GetSettingUseCase
) : ViewModel() {

    private val _settingData = MutableSharedFlow<SettingData>()
    val settingData: SharedFlow<SettingData> = _settingData

    init {
        viewModelScope.launch {
            getSettingUseCase.execute().collect { settings ->
                _settingData.emit(settings)
            }
        }
    }
}
