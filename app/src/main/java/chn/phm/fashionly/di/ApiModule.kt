package chn.phm.fashionly.di

import chn.phm.data.remote.FashionlyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    @Singleton
    fun fashionlyApi(retrofit: Retrofit): FashionlyApi = retrofit.create(FashionlyApi::class.java)
}
