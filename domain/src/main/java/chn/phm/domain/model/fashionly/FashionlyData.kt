package chn.phm.domain.model.fashionly

data class FashionlyData(
    val key: String,
    val prompt: String,
    val negativePrompt: String,
    val modelImage: String,
    val clothImage: String,
    val clothType: String,
    val height: Int,
    val width: Int,
    val guidanceScale: Double,
    val numInferenceSteps: Int,
    val seed: Long,
    val temp: String,
    val webhook: String?,
    val trackId: String?
)
