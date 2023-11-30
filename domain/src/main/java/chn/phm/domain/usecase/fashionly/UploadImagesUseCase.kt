package chn.phm.domain.usecase.fashionly

import android.net.Uri
import chn.phm.domain.base.SingleUseCaseWithParameter
import chn.phm.domain.repository.FashionlyRepository
import javax.inject.Inject

class UploadImagesUseCase @Inject constructor(
    private val fashionlyRepository: FashionlyRepository
) : SingleUseCaseWithParameter<List<Uri>, Result<List<String>>> {

    override suspend fun execute(parameter: List<Uri>): Result<List<String>> {
        return fashionlyRepository.uploadImages(
            parameter,
        )
    }
}
