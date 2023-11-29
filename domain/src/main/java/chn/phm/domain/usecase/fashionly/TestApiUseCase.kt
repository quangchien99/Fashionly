package chn.phm.domain.usecase.fashionly

import chn.phm.domain.base.SingleUseCase
import chn.phm.domain.repository.FashionlyRepository
import javax.inject.Inject

class TestApiUseCase @Inject constructor(
    private val fashionlyRepository: FashionlyRepository
) : SingleUseCase<String> {

    override suspend fun execute(): String {
        return fashionlyRepository.testApi()
    }
}
