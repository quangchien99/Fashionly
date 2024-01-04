package chn.phm.fashionly.di

import android.content.ContentResolver
import androidx.datastore.core.DataStore
import chn.phm.data.FashionlyPreferences
import chn.phm.data.local.database.dao.FashionlyResultDao
import chn.phm.data.local.preference.FashionlyPrefManager
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
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideFashionlyPrefManager(
        fashionlyPreferences: DataStore<FashionlyPreferences>
    ): FashionlyPrefManager {
        return FashionlyPrefManager(fashionlyPreferences)
    }

    @Provides
    @ViewModelScoped
    fun provideSettingRepository(
        fashionlyPrefManager: FashionlyPrefManager
    ): SettingRepository {
        return SettingRepositoryImpl(fashionlyPrefManager)
    }

    @Provides
    @ViewModelScoped
    fun provideFashionlyRepository(
        fashionlyApi: FashionlyApi,
        storageReference: StorageReference,
        deviceInfoProvider: DeviceInfoProvider,
        contentResolver: ContentResolver,
        fashionlyResultDao: FashionlyResultDao
    ): FashionlyRepository {
        return FashionlyRepositoryImpl(
            fashionlyApi,
            storageReference,
            deviceInfoProvider,
            contentResolver,
            fashionlyResultDao
        )
    }

    @Provides
    @ViewModelScoped
    fun provideRemoteConfigRepository(firebaseRemoteConfig: FirebaseRemoteConfig): RemoteConfigRepository {
        return RemoteConfigRepositoryImpl(firebaseRemoteConfig)
    }
}
