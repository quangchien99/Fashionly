package chn.phm.domain.usecase.fashionly

import chn.phm.domain.base.SingleUseCaseWithParameter
import chn.phm.domain.repository.FashionlyRepository
import javax.inject.Inject

class SaveImageUseCase @Inject constructor(
    private val fashionlyRepository: FashionlyRepository
) : SingleUseCaseWithParameter<String, Result<Boolean>> {

    override suspend fun execute(parameter: String): Result<Boolean> {
        return fashionlyRepository.saveImageToStorage(parameter)
    }
}
