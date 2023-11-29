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
            if (isFetchRemoteConfigSuccess) {
                val githubAccessToken =
                    getConfigValueUseCase.execute("fashionly_github_access_token")
                Log.e("Chien", "githubAccessToken= $githubAccessToken")
            } else {
                Log.e("Chien", "isFetchRemoteConfigSuccess= $isFetchRemoteConfigSuccess")
            }
            getSettingUseCase.execute().collect { settings ->
                _settingData.emit(settings)
            }
        }
    }
}
