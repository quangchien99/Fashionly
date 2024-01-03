package chn.phm.domain.model.fashionly

data class FashionlyResultDomain(
    val id: Int = -1,
    val timeCreated: Long,
    val modelImageUri: String,
    val clothImageUri: String,
    val resultUrl: String,
    val prompt: String,
    val clothType: String,
)
