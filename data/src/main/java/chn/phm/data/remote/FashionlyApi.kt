package chn.phm.data.remote

import chn.phm.data.remote.network.BaseApiService
import chn.phm.data.remote.network.GenericNetworkResponse
import retrofit2.http.GET

interface FashionlyApi : BaseApiService {

    @GET("success")
    suspend fun testApi(): GenericNetworkResponse<String>
}
