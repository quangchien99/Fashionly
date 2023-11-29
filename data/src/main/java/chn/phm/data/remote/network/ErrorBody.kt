package chn.phm.data.remote.network

data class ErrorBody(
    val statusCode: Int,
    val error: String,
    val message: String,
)
