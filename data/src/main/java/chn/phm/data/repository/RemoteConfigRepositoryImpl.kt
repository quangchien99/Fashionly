package chn.phm.data.repository

import chn.phm.domain.repository.RemoteConfigRepository
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteConfigRepositoryImpl @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) : RemoteConfigRepository {

    override suspend fun fetchAndActivate(): Boolean {
        return firebaseRemoteConfig.fetchAndActivate().await()
    }

    override fun getStringConfigValue(key: String): String {
        return firebaseRemoteConfig.getString(key)
    }
}
