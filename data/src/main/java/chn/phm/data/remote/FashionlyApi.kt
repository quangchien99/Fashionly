package chn.phm.data.remote

import chn.phm.data.remote.network.BaseApiService
import chn.phm.data.remote.network.GenericNetworkResponse
import chn.phm.data.remote.request.FashionlyRequest
import chn.phm.data.remote.response.FashionlyResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface FashionlyApi : BaseApiService {

    @GET("success")
    suspend fun testApi(): GenericNetworkResponse<String>

    @Headers("Content-Type: application/json")
    @POST("api/v5/fashion")
    suspend fun fashionize(
        @Body fashionlyRequest: FashionlyRequest
    ): GenericNetworkResponse<FashionlyResponse>
}
