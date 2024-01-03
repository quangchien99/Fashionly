package chn.phm.domain.repository

import android.net.Uri
import chn.phm.domain.model.fashionly.FashionlyData
import chn.phm.domain.model.fashionly.FashionlyResult
import chn.phm.domain.model.fashionly.FashionlyResultDomain
import kotlinx.coroutines.flow.Flow

interface FashionlyRepository {

    val allFashionlyResults: Flow<List<FashionlyResultDomain>>

    suspend fun uploadImages(
        uris: List<Uri>
    ): Result<List<String>>

    suspend fun fashionize(fashionlyData: FashionlyData): Result<FashionlyResult>

    suspend fun saveImageToStorage(imageUrl: String): Result<Boolean>

    suspend fun insertFashionlyResult(fashionlyResultDomain: FashionlyResultDomain): Result<Int>
}
