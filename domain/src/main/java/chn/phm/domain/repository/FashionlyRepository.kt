package chn.phm.domain.repository

import android.net.Uri

interface FashionlyRepository {

    suspend fun testApi(): String

    suspend fun uploadImages(
        uris: List<Uri>
    ): Result<List<String>>
}
