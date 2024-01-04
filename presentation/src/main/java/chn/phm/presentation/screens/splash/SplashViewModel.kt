package chn.phm.presentation.screens.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.model.setting.FashionlyPrefDomain
import chn.phm.domain.usecase.remoteconfig.FetchAndActivateConfigUseCase
import chn.phm.domain.usecase.setting.GetSettingUseCase
import chn.phm.domain.usecase.setting.UpdateFashionlySettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSettingUseCase: GetSettingUseCase,
    private val fetchAndActivateConfigUseCase: FetchAndActivateConfigUseCase,
    private val updateFashionlySettingsUseCase: UpdateFashionlySettingsUseCase
) : ViewModel() {

    private val _isFirstOpen = MutableLiveData<Boolean>()
    val isFirstOpen: LiveData<Boolean> = _isFirstOpen

    init {
        viewModelScope.launch {
            val isFetchRemoteConfigSuccess = fetchAndActivateConfigUseCase.execute()
            Log.e("Fashionly", "fetchAndActivateConfigUseCase= $isFetchRemoteConfigSuccess")

            val settings = getSettingUseCase.execute().firstOrNull()
            Log.e("Fashionly", "SplashViewModel: getSettingUseCase Settings = $settings")

            settings?.let { fashionlyPrefs ->
                _isFirstOpen.value = fashionlyPrefs.isFirstOpen

                // In case no default value is set for pref
                if (fashionlyPrefs.fashionlySettings.height == 0) {
                    updateFashionlySettingsUseCase.execute(
                        FashionlyPrefDomain.FashionlySettingsDomain()
                    )
                }
            }
        }
    }
}
