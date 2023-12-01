package chn.phm.data.remote.response

import com.google.gson.annotations.SerializedName

data class FashionlyResponse(
    val status: String,
    @SerializedName("generationTime") val generationTime: Double,
    val id: Int,
    val output: List<String>,
    @SerializedName("proxy_links") val proxyLinks: List<String>,
    val meta: MetaInfo
)

data class MetaInfo(
    val cloth: String,
    @SerializedName("cloth_type") val clothType: String,
    @SerializedName("file_prefix") val filePrefix: String,
    @SerializedName("guidance_scale") val guidanceScale: Double,
    val height: Int,
    @SerializedName("image") val initImage: String,
    @SerializedName("negative_prompt") val negativePrompt: String,
    @SerializedName("num_inference_steps") val numInferenceSteps: Int,
    val outdir: String,
    val prompt: String,
    val seed: Long,
    val temp: String,
    val width: Int
)
