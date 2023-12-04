package chn.phm.data.repository

import chn.phm.domain.repository.RemoteConfigRepository
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteConfigRepositoryImpl @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) : RemoteConfigRepository {

    override suspend fun fetchAndActivate(): Boolean {
        return firebaseRemoteConfig.fetchAndActivate().await().also {
            firebaseRemoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
                override fun onUpdate(configUpdate: ConfigUpdate) {
                    firebaseRemoteConfig.activate()
                }

                override fun onError(error: FirebaseRemoteConfigException) {
                    // handle later
                }
            })
        }
    }

    override fun getStringConfigValue(key: String): String {
        return firebaseRemoteConfig.getString(key)
    }
}
