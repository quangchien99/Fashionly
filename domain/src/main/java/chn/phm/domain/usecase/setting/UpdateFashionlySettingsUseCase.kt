package chn.phm.domain.usecase.setting

import chn.phm.domain.base.SingleUseCaseWithParameter
import chn.phm.domain.model.setting.FashionlyPrefDomain
import chn.phm.domain.repository.SettingRepository
import javax.inject.Inject

class UpdateFashionlySettingsUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) : SingleUseCaseWithParameter<FashionlyPrefDomain.FashionlySettingsDomain, Unit> {

    override suspend fun execute(parameter: FashionlyPrefDomain.FashionlySettingsDomain) {
        settingRepository.setFashionlySetting(parameter)
    }
}
