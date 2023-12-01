package chn.phm.data.remote.request

import com.google.gson.annotations.SerializedName

data class FashionlyRequest(
    val key: String,
    val prompt: String,
    @SerializedName("negative_prompt") val negativePrompt: String,
    @SerializedName("init_image") val modelImage: String,
    @SerializedName("cloth_image") val clothImage: String,
    @SerializedName("cloth_type") val clothType: String,
    val height: Int,
    val width: Int,
    @SerializedName("guidance_scale") val guidanceScale: Double,
    @SerializedName("num_inference_steps") val numInferenceSteps: Int,
    val seed: Long,
    val temp: String,
    val webhook: String?,
    @SerializedName("track_id") val trackId: String?
)
