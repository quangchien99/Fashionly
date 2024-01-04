package chn.phm.presentation.screens.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.model.setting.FashionlyPrefDomain
import chn.phm.domain.usecase.setting.GetSettingUseCase
import chn.phm.domain.usecase.setting.UpdateFashionlySettingsUseCase
import chn.phm.domain.usecase.setting.UpdateIsEnableDarkModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingUseCase: GetSettingUseCase,
    private val updateFashionlySettingsUseCase: UpdateFashionlySettingsUseCase,
    private val updateIsEnableDarkModeUseCase: UpdateIsEnableDarkModeUseCase
) : ViewModel() {

    val currentSettings: StateFlow<FashionlyPrefDomain> = getSettingUseCase.execute().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        FashionlyPrefDomain()
    )

    fun updateIsEnableDarkMode(isEnable: Boolean) {
        viewModelScope.launch {
            updateIsEnableDarkModeUseCase.execute(isEnable)
        }
    }

    fun updateFashionlySettings(
        negativePrompt: String,
        height: Int,
        width: Int,
        guidanceScale: Double,
        numInferenceSteps: Int,
        seed: Int
    ) {
        viewModelScope.launch {
            updateFashionlySettingsUseCase.execute(
                FashionlyPrefDomain.FashionlySettingsDomain(
                    negativePrompt = negativePrompt,
                    height = height,
                    width = width,
                    guidanceScale = guidanceScale,
                    numInferenceSteps = numInferenceSteps,
                    seed = seed
                )
            )
        }
    }
}
