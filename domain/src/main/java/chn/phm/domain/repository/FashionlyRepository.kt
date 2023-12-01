package chn.phm.domain.repository

import android.net.Uri
import chn.phm.domain.model.fashionly.FashionlyData
import chn.phm.domain.model.fashionly.FashionlyResult

interface FashionlyRepository {

    suspend fun uploadImages(
        uris: List<Uri>
    ): Result<List<String>>

    suspend fun fashionize(fashionlyData: FashionlyData): Result<FashionlyResult>
}
