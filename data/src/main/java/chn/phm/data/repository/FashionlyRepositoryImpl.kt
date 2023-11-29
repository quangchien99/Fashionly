package chn.phm.data.repository

import chn.phm.data.remote.FashionlyApi
import chn.phm.data.remote.network.NetworkResponse
import chn.phm.domain.repository.FashionlyRepository
import javax.inject.Inject

class FashionlyRepositoryImpl @Inject constructor(
    private val fashionlyApi: FashionlyApi
) : FashionlyRepository {

    override suspend fun testApi(): String {
        val response = fashionlyApi.testApi()
        return when (response) {
            is NetworkResponse.Success -> {
                response.body
            }
            is NetworkResponse.ApiError -> {
                "ApiError $response"
            }
            is NetworkResponse.NetworkError -> {
                "NetworkError ${response.error}"
            }
            is NetworkResponse.UnknownError -> {
                "UnknownError ${response.error}"
            }
        }
    }
}
