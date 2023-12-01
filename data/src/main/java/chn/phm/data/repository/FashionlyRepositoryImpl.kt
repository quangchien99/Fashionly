package chn.phm.data.repository

import android.net.Uri
import chn.phm.data.mapper.toFashionlyRequest
import chn.phm.data.mapper.toFashionlyResult
import chn.phm.data.remote.FashionlyApi
import chn.phm.data.remote.network.NetworkResponse
import chn.phm.data.utils.DeviceInfoProvider
import chn.phm.domain.model.fashionly.FashionlyData
import chn.phm.domain.model.fashionly.FashionlyResult
import chn.phm.domain.repository.FashionlyRepository
import com.google.android.gms.tasks.Tasks
import com.google.firebase.storage.StorageReference
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FashionlyRepositoryImpl @Inject constructor(
    private val fashionlyApi: FashionlyApi,
    private val storageReference: StorageReference,
    private val deviceInfoProvider: DeviceInfoProvider
) : FashionlyRepository {

    override suspend fun uploadImages(uris: List<Uri>): Result<List<String>> =
        suspendCoroutine { continuation ->
            val uploadTasks = uris.mapIndexed { index, uri ->
                val ref = when (index) {
                    0 -> storageReference.child("fashionly/${deviceInfoProvider.androidId}/image-model")
                    1 -> storageReference.child("fashionly/${deviceInfoProvider.androidId}/image-cloth")
                    else -> storageReference.child("fashionly/${deviceInfoProvider.androidId}/${uri.lastPathSegment}")
                }
                ref.putFile(uri).continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let { throw it }
                    }
                    ref.downloadUrl
                }
            }

            Tasks.whenAllSuccess<Uri>(uploadTasks)
                .addOnSuccessListener { downloadUris ->
                    val urls = downloadUris.map { it.toString() }
                    continuation.resume(Result.success(urls))
                }
                .addOnFailureListener { exception ->
                    continuation.resume(Result.failure(exception))
                }
        }

    override suspend fun fashionize(fashionlyData: FashionlyData): Result<FashionlyResult> {
        return when (val response = fashionlyApi.fashionize(fashionlyData.toFashionlyRequest())) {
            is NetworkResponse.Success -> {
                try {
                    Result.success(response.body.toFashionlyResult())
                } catch (e: Exception) {
                    Result.failure(Exception("ApiError $response"))
                }
            }
            is NetworkResponse.ApiError -> {
                Result.failure(Exception("ApiError $response"))
            }
            is NetworkResponse.NetworkError -> {
                Result.failure(Exception("NetworkError ${response.error}"))
            }
            is NetworkResponse.UnknownError -> {
                Result.failure(Exception("UnknownError ${response.error}"))
            }
        }
    }
}
