package chn.phm.domain.usecase.setting

import chn.phm.domain.base.SingleUseCaseFlow
import chn.phm.domain.model.setting.FashionlyPrefDomain
import chn.phm.domain.repository.SettingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) : SingleUseCaseFlow<Flow<FashionlyPrefDomain>> {
    override fun execute(): Flow<FashionlyPrefDomain> {
        return settingRepository.fashionlyPrefs
    }
}
