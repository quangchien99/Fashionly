package chn.phm.domain.model.fashionly

data class FashionlyResult(
    val status: String,
    val generationTime: Double,
    val id: Int,
    val output: List<String>,
    val proxyLinks: List<String>,
    val meta: MetaInfoDomain
)

data class MetaInfoDomain(
    val cloth: String,
    val clothType: String,
    val filePrefix: String,
    val guidanceScale: Double,
    val height: Int,
    val initImage: String,
    val negativePrompt: String,
    val numInferenceSteps: Int,
    val outdir: String,
    val prompt: String,
    val seed: Long,
    val temp: String,
    val width: Int
)
