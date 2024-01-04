package chn.phm.data.repository

import chn.phm.data.local.preference.FashionlyPrefManager
import chn.phm.domain.model.setting.FashionlyPrefDomain
import chn.phm.domain.repository.SettingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingRepositoryImpl @Inject constructor(
    private val fashionlyPrefManager: FashionlyPrefManager
) : SettingRepository {

    override val fashionlyPrefs: Flow<FashionlyPrefDomain> =
        fashionlyPrefManager.fashionlyPrefsData.map {
            FashionlyPrefDomain(
                isFirstOpen = !it.isDisableOnBoarding,
                isEnableDarkMode = it.isEnabledDarkMode,
                fashionlySettings = FashionlyPrefDomain.FashionlySettingsDomain(
                    negativePrompt = it.settings.negativePrompt,
                    height = it.settings.height,
                    width = it.settings.width,
                    guidanceScale = it.settings.guidanceScale,
                    numInferenceSteps = it.settings.numInferenceSteps,
                    seed = it.settings.seed.toInt()
                )
            )
        }

    override suspend fun markOpened() {
        fashionlyPrefManager.markOpened()
    }

    override suspend fun setIsEnableDarkMode(isEnableDarkMode: Boolean) {
        fashionlyPrefManager.setIsEnableDarkMode(isEnableDarkMode)
    }

    override suspend fun setFashionlySetting(fashionlySettingsDomain: FashionlyPrefDomain.FashionlySettingsDomain) {
        fashionlyPrefManager.setFashionlySetting(
            negativePrompt = fashionlySettingsDomain.negativePrompt,
            height = fashionlySettingsDomain.height,
            width = fashionlySettingsDomain.width,
            guidanceScale = fashionlySettingsDomain.guidanceScale,
            numInferenceSteps = fashionlySettingsDomain.numInferenceSteps,
            seed = fashionlySettingsDomain.seed
        )
    }
}
