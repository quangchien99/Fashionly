package chn.phm.data.mapper

import chn.phm.data.local.database.entity.FashionlyResultEntity
import chn.phm.data.remote.request.FashionlyRequest
import chn.phm.data.remote.response.FashionlyResponse
import chn.phm.domain.model.fashionly.FashionlyData
import chn.phm.domain.model.fashionly.FashionlyResult
import chn.phm.domain.model.fashionly.FashionlyResultDomain
import chn.phm.domain.model.fashionly.MetaInfoDomain

fun FashionlyData.toFashionlyRequest(): FashionlyRequest {
    return FashionlyRequest(
        key = this.key,
        prompt = this.prompt,
        negativePrompt = this.negativePrompt,
        modelImage = this.modelImage,
        clothImage = this.clothImage,
        clothType = this.clothType,
        height = this.height,
        width = this.width,
        guidanceScale = this.guidanceScale,
        numInferenceSteps = this.numInferenceSteps,
        seed = this.seed,
        temp = this.temp,
        webhook = this.webhook,
        trackId = this.trackId
    )
}

fun FashionlyResponse.toFashionlyResult(): FashionlyResult {
    return FashionlyResult(
        status = this.status,
        generationTime = this.generationTime,
        id = this.id,
        output = this.output,
        proxyLinks = this.proxyLinks,
        meta = MetaInfoDomain(
            cloth = this.meta.cloth,
            clothType = this.meta.clothType,
            filePrefix = this.meta.filePrefix,
            guidanceScale = this.meta.guidanceScale,
            height = this.meta.height,
            initImage = this.meta.initImage,
            negativePrompt = this.meta.negativePrompt,
            numInferenceSteps = this.meta.numInferenceSteps,
            outdir = this.meta.outdir,
            prompt = this.meta.prompt,
            seed = this.meta.seed,
            temp = this.meta.temp,
            width = this.meta.width
        )
    )
}

fun FashionlyResultDomain.toEntityModel(): FashionlyResultEntity {
    return FashionlyResultEntity(
        modelImageUri = this.modelImageUri,
        clothImageUri = this.clothImageUri,
        resultUrl = this.resultUrl,
        prompt = this.prompt
    )
}

fun FashionlyResultEntity.toDomainModel(): FashionlyResultDomain {
    return FashionlyResultDomain(
        id = id,
        modelImageUri = modelImageUri,
        clothImageUri = clothImageUri,
        resultUrl = resultUrl,
        prompt = prompt
    )
}
