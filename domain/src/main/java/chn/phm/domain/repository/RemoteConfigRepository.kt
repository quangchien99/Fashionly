package chn.phm.domain.repository

interface RemoteConfigRepository {

    suspend fun fetchAndActivate(): Boolean

    fun getStringConfigValue(key: String): String
}
