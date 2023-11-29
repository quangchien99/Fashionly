package chn.phm.domain.usecase.remoteconfig

import chn.phm.domain.base.SingleUseCase
import chn.phm.domain.repository.RemoteConfigRepository
import javax.inject.Inject

class FetchAndActivateConfigUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository
) : SingleUseCase<Boolean> {

    override suspend fun execute(): Boolean {
        return remoteConfigRepository.fetchAndActivate()
    }
}
