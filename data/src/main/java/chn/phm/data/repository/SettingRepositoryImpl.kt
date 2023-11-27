package chn.phm.data.repository

import chn.phm.data.local.preference.SettingDataStoreConstants.ENABLED_NIGHT_MODE
import chn.phm.data.local.preference.SettingDataStoreConstants.IS_FIRST_OPEN
import chn.phm.data.local.preference.SettingStoreManager
import chn.phm.domain.model.setting.SettingData
import chn.phm.domain.repository.SettingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingRepositoryImpl @Inject constructor(
    private val settingStoreManager: SettingStoreManager
) : SettingRepository {
    override suspend fun putSetting(setting: SettingData) {
        settingStoreManager.putPreference(key = IS_FIRST_OPEN, value = setting.isFirstOpen)
        settingStoreManager.putPreference(key = ENABLED_NIGHT_MODE, value = setting.isNightMode)
    }

    override suspend fun getSettingData(): Flow<SettingData> {
        val isFirstOpenFlow = settingStoreManager.getPreference(
            key = IS_FIRST_OPEN,
            defaultValue = true
        )
        val enabledNightModeFlow = settingStoreManager.getPreference(
            key = ENABLED_NIGHT_MODE,
            defaultValue = false
        )

        return isFirstOpenFlow.combine(enabledNightModeFlow) { isFirstOpen, enabledNightMode ->
            SettingData(
                isFirstOpen = isFirstOpen,
                isNightMode = enabledNightMode
            )
        }
    }
}
