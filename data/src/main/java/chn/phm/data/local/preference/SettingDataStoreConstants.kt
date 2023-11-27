package chn.phm.data.local.preference

import androidx.datastore.preferences.core.booleanPreferencesKey

internal object SettingDataStoreConstants {
    const val SETTING_DATASTORE_NAME = "SETTING_DATASTORE_NAME"

    val IS_FIRST_OPEN = booleanPreferencesKey("is_first_open")
    val ENABLED_NIGHT_MODE = booleanPreferencesKey("enabled_night_mode")
}
