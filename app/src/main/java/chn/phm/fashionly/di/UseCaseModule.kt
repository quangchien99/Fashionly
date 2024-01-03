package chn.phm.fashionly.di

import chn.phm.domain.repository.FashionlyRepository
import chn.phm.domain.repository.RemoteConfigRepository
import chn.phm.domain.repository.SettingRepository
import chn.phm.domain.usecase.fashionly.FashionizeUseCase
import chn.phm.domain.usecase.fashionly.GetAllFashionlyResultsUseCase
import chn.phm.domain.usecase.fashionly.InsertFashionlyResultUseCase
import chn.phm.domain.usecase.fashionly.SaveImageUseCase
import chn.phm.domain.usecase.fashionly.UploadImagesUseCase
import chn.phm.domain.usecase.remoteconfig.FetchAndActivateConfigUseCase
import chn.phm.domain.usecase.remoteconfig.GetConfigValueUseCase
import chn.phm.domain.usecase.setting.GetSettingUseCase
import chn.phm.domain.usecase.setting.SaveSettingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    /*
     For Settings
     */
    @Provides
    fun provideGetSettingUseCase(settingRepository: SettingRepository): GetSettingUseCase {
        return GetSettingUseCase(settingRepository)
    }

    @Provides
    fun provideSaveSettingUseCase(settingRepository: SettingRepository): SaveSettingUseCase {
        return SaveSettingUseCase(settingRepository)
    }

    /*
    For Remote Config
    */
    @Provides
    fun provideFetchAndActivateConfigUseCase(
        remoteConfigRepository: RemoteConfigRepository
    ): FetchAndActivateConfigUseCase {
        return FetchAndActivateConfigUseCase(remoteConfigRepository)
    }

    @Provides
    fun provideGetConfigValueUseCase(
        remoteConfigRepository: RemoteConfigRepository
    ): GetConfigValueUseCase {
        return GetConfigValueUseCase(remoteConfigRepository)
    }

    /*
    For Fashionly
    */
    @Provides
    fun provideFashionizeUseCase(fashionlyRepository: FashionlyRepository): FashionizeUseCase {
        return FashionizeUseCase(fashionlyRepository)
    }

    @Provides
    fun provideSaveImageUseCase(fashionlyRepository: FashionlyRepository): SaveImageUseCase {
        return SaveImageUseCase(fashionlyRepository)
    }

    @Provides
    fun provideUploadImageUseCase(fashionlyRepository: FashionlyRepository): UploadImagesUseCase {
        return UploadImagesUseCase(fashionlyRepository)
    }

    @Provides
    fun provideGetAllFashionlyResultUseCase(
        fashionlyRepository: FashionlyRepository
    ): GetAllFashionlyResultsUseCase {
        return GetAllFashionlyResultsUseCase(fashionlyRepository)
    }

    @Provides
    fun provideInsertFashionlyResultUseCase(
        fashionlyRepository: FashionlyRepository
    ): InsertFashionlyResultUseCase {
        return InsertFashionlyResultUseCase(fashionlyRepository)
    }
}
