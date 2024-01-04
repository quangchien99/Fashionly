package chn.phm.domain.repository

import chn.phm.domain.model.setting.FashionlyPrefDomain
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    val fashionlyPrefs: Flow<FashionlyPrefDomain>

    suspend fun markOpened()

    suspend fun setIsEnableDarkMode(isEnableDarkMode: Boolean)

    suspend fun setFashionlySetting(fashionlySettingsDomain: FashionlyPrefDomain.FashionlySettingsDomain)
}
