package chn.phm.data.remote.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRetrofit @Inject constructor(
    okHttpClient: OkHttpClient,
    gson: Gson
) {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://stablediffusionapi.com/")
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
}
