package chn.phm.fashionly.di

import android.content.Context
import chn.phm.data.local.preference.SettingStoreManager
import chn.phm.data.repository.SettingRepositoryImpl
import chn.phm.domain.repository.SettingRepository
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
}
