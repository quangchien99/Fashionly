package chn.phm.domain.usecase.remoteconfig

import chn.phm.domain.base.SingleUseCaseWithParameter
import chn.phm.domain.repository.RemoteConfigRepository
import javax.inject.Inject

class GetConfigValueUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository
) : SingleUseCaseWithParameter<String, String> {

    override suspend fun execute(parameter: String): String {
        return remoteConfigRepository.getStringConfigValue(parameter)
    }
}
