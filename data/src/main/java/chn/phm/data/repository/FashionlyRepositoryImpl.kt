package chn.phm.data.repository

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FashionlyRepositoryImpl @Inject constructor(
    private val fashionlyApi: FashionlyApi,
    private val storageReference: StorageReference,
    private val deviceInfoProvider: DeviceInfoProvider,
    private val contentResolver: ContentResolver
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

            Tasks.whenAllSuccess<Uri>(uploadTasks).addOnSuccessListener { downloadUris ->
                val urls = downloadUris.map { it.toString() }
                continuation.resume(Result.success(urls))
            }.addOnFailureListener { exception ->
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

    override suspend fun saveImageToStorage(imageUrl: String): Result<Boolean> {
        Log.e("Fashionly", " Repository saveImageToStorage $imageUrl")
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(imageUrl)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val inputStream: InputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)
                Log.e("Fashionly", "saveImageToStorage $bitmap")
                val result = saveBitmapToStorage(bitmap)
                Result.success(result)
            } catch (e: Exception) {
                Log.e("Fashionly", "exception= $e")
                Result.failure(e)
            }
        }
    }

    private fun saveBitmapToStorage(bitmap: Bitmap): Boolean {
        val filename = "image_${System.currentTimeMillis()}.jpg"

        return try {
            var fos: OutputStream? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val imageUri: Uri? = contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues
                )
                fos = imageUri?.let { contentResolver.openOutputStream(it) }
            } else {
                val imagesDir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val image = File(imagesDir, filename)
                fos = FileOutputStream(image)
            }

            fos?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                true
            } ?: false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
