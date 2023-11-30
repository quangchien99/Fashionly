package chn.phm.fashionly.di

import android.content.Context
import chn.phm.data.local.preference.SettingStoreManager
import chn.phm.data.remote.FashionlyApi
import chn.phm.data.repository.FashionlyRepositoryImpl
import chn.phm.data.repository.RemoteConfigRepositoryImpl
import chn.phm.data.repository.SettingRepositoryImpl
import chn.phm.data.utils.DeviceInfoProvider
import chn.phm.domain.repository.FashionlyRepository
import chn.phm.domain.repository.RemoteConfigRepository
import chn.phm.domain.repository.SettingRepository
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideSettingStoreManager(@ApplicationContext context: Context): SettingStoreManager {
        return SettingStoreManager(context)
    }

    @Provides
    @ViewModelScoped
    fun provideSettingRepository(settingStoreManager: SettingStoreManager): SettingRepository {
        return SettingRepositoryImpl(settingStoreManager)
    }

    @Provides
    @ViewModelScoped
    fun provideFashionlyRepository(
        fashionlyApi: FashionlyApi,
        storageReference: StorageReference,
        deviceInfoProvider: DeviceInfoProvider
    ): FashionlyRepository {
        return FashionlyRepositoryImpl(fashionlyApi, storageReference, deviceInfoProvider)
    }

    @Provides
    @ViewModelScoped
    fun provideRemoteConfigRepository(firebaseRemoteConfig: FirebaseRemoteConfig): RemoteConfigRepository {
        return RemoteConfigRepositoryImpl(firebaseRemoteConfig)
    }
}
