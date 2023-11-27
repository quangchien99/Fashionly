package chn.phm.data.local.preference

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import chn.phm.data.local.preference.SettingDataStoreConstants.SETTING_DATASTORE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(
    name = SETTING_DATASTORE_NAME
)

@Singleton
class SettingStoreManager @Inject constructor(@ApplicationContext context: Context) {
    private val settingDataStore = context.dataStore

    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        settingDataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val result = preferences[key] ?: defaultValue
            result
        }

    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        settingDataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}
