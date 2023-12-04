package chn.phm.presentation.screens.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.model.setting.SettingData
import chn.phm.domain.usecase.remoteconfig.FetchAndActivateConfigUseCase
import chn.phm.domain.usecase.remoteconfig.GetConfigValueUseCase
import chn.phm.domain.usecase.setting.GetSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSettingUseCase: GetSettingUseCase,
    private val fetchAndActivateConfigUseCase: FetchAndActivateConfigUseCase,
    private val getConfigValueUseCase: GetConfigValueUseCase
) : ViewModel() {

    private val _settingData = MutableSharedFlow<SettingData>()
    val settingData: SharedFlow<SettingData> = _settingData

    init {
        viewModelScope.launch {
            val isFetchRemoteConfigSuccess = fetchAndActivateConfigUseCase.execute()
            Log.e("Chien", "fetchAndActivateConfigUseCase= $isFetchRemoteConfigSuccess")
            if (isFetchRemoteConfigSuccess) {
                val stableDiffusionApiKey =
                    getConfigValueUseCase.execute("stable_diffusion_api_key")
                Log.e("Chien", "stable_diffusion_api_key= $stableDiffusionApiKey")
            } else {
                val stableDiffusionApiKey =
                    getConfigValueUseCase.execute("stable_diffusion_api_key")
                Log.e("Chien", "stable_diffusion_api_key= $stableDiffusionApiKey")
            }
            getSettingUseCase.execute().collect { settings ->
                _settingData.emit(settings)
            }
        }
    }
}
