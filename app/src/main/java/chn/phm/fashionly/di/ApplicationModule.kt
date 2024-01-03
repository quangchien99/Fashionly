package chn.phm.fashionly.di

import android.content.ContentResolver
import android.content.Context
import androidx.room.Room
import chn.phm.data.local.database.FashionlyDatabase
import chn.phm.data.local.database.dao.FashionlyResultDao
import chn.phm.data.remote.network.NetworkClient
import chn.phm.data.remote.network.NetworkRetrofit
import chn.phm.data.utils.DeviceInfoProvider
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .disableHtmlEscaping()
        .create()

    @Provides
    @Singleton
    fun okHttpClient(networkClient: NetworkClient): OkHttpClient = networkClient.okHttpClient

    @Provides
    @Singleton
    fun retrofit(networkRetrofit: NetworkRetrofit): Retrofit = networkRetrofit.retrofit

    @Singleton
    @Provides
    fun provideStorageReference() = Firebase.storage.reference

    @Singleton
    @Provides
    fun provideDeviceInfoProvider(@ApplicationContext context: Context): DeviceInfoProvider {
        return DeviceInfoProvider(context)
    }

    @Singleton
    @Provides
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver {
        return context.contentResolver
    }

    @Singleton
    @Provides
    fun provideFashionlyDatabase(@ApplicationContext context: Context): FashionlyDatabase {
        return Room.databaseBuilder(
            context,
            FashionlyDatabase::class.java,
            "FashionlyDatabase.db"
        ).build()
    }

    @Provides
    fun provideFashionlyResultDao(fashionlyDatabase: FashionlyDatabase): FashionlyResultDao {
        return fashionlyDatabase.fashionlyResultDao()
    }
}
