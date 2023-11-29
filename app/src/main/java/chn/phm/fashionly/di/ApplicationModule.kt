package chn.phm.fashionly.di

import chn.phm.data.remote.network.NetworkClient
import chn.phm.data.remote.network.NetworkRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun okHttpClient(networkClient: NetworkClient): OkHttpClient = networkClient.okHttpClient

    @Provides
    @Singleton
    fun retrofit(networkRetrofit: NetworkRetrofit): Retrofit = networkRetrofit.retrofit
}
