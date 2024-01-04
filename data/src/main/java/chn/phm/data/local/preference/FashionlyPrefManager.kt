package chn.phm.data.local.preference

import androidx.datastore.core.DataStore
import chn.phm.data.FashionlyPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FashionlyPrefManager @Inject constructor(
    private val fashionlyPreferences: DataStore<FashionlyPreferences>
) {
    val fashionlyPrefsData: Flow<FashionlyPreferences> = fashionlyPreferences.data

    suspend fun markOpened() {
        fashionlyPreferences.updateData { currentPreferences ->
            currentPreferences.toBuilder()
                .setIsDisableOnBoarding(true)
                .build()
        }
    }

    suspend fun setIsEnableDarkMode(isEnableDarkMode: Boolean) {
        fashionlyPreferences.updateData { currentPreferences ->
            currentPreferences.toBuilder()
                .setIsEnabledDarkMode(isEnableDarkMode)
                .build()
        }
    }

    suspend fun setFashionlySetting(
        negativePrompt: String,
        height: Int,
        width: Int,
        guidanceScale: Double,
        numInferenceSteps: Int,
        seed: Int
    ) {
        fashionlyPreferences.updateData { currentPreferences ->
            currentPreferences.toBuilder()
                .setSettings(
                    FashionlyPreferences.FashionlySettings.newBuilder()
                        .setNegativePrompt(negativePrompt)
                        .setHeight(height)
                        .setWidth(width)
                        .setGuidanceScale(guidanceScale)
                        .setNumInferenceSteps(numInferenceSteps)
                        .setSeed(seed.toLong())
                        .build()
                )
                .build()
        }
    }
}
