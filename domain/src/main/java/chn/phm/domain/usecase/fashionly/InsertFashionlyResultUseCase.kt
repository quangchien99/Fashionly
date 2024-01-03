package chn.phm.domain.usecase.fashionly

import chn.phm.domain.base.SingleUseCaseWithParameter
import chn.phm.domain.model.fashionly.FashionlyResultDomain
import chn.phm.domain.repository.FashionlyRepository
import javax.inject.Inject

class InsertFashionlyResultUseCase @Inject constructor(
    private val fashionlyRepository: FashionlyRepository
) : SingleUseCaseWithParameter<FashionlyResultDomain, Result<Int>> {

    override suspend fun execute(parameter: FashionlyResultDomain): Result<Int> {
        return fashionlyRepository.insertFashionlyResult(parameter)
    }
}
