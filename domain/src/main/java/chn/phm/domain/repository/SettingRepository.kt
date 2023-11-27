package chn.phm.domain.repository

import chn.phm.domain.model.setting.SettingData
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    suspend fun putSetting(setting: SettingData)

    suspend fun getSettingData(): Flow<SettingData>
}
