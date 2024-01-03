package chn.phm.domain.usecase.fashionly

import chn.phm.domain.base.SingleUseCaseFlow
import chn.phm.domain.model.fashionly.FashionlyResultDomain
import chn.phm.domain.repository.FashionlyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFasionlyResultsUseCase @Inject constructor(
    private val fashionlyRepository: FashionlyRepository
) : SingleUseCaseFlow<Flow<List<FashionlyResultDomain>>> {

    override fun execute(): Flow<List<FashionlyResultDomain>> {
        return fashionlyRepository.allFashionlyResults
    }
}
