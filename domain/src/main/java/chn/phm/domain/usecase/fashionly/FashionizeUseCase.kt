package chn.phm.domain.usecase.fashionly

import chn.phm.domain.base.SingleUseCaseWithParameter
import chn.phm.domain.model.fashionly.FashionlyData
import chn.phm.domain.model.fashionly.FashionlyResult
import chn.phm.domain.repository.FashionlyRepository
import javax.inject.Inject

class FashionizeUseCase @Inject constructor(
    private val fashionlyRepository: FashionlyRepository
) : SingleUseCaseWithParameter<FashionlyData, Result<FashionlyResult>> {

    override suspend fun execute(parameter: FashionlyData): Result<FashionlyResult> {
        return fashionlyRepository.fashionize(parameter)
    }
}
