package chn.phm.domain.usecase.setting

import chn.phm.domain.base.SingleUseCaseWithParameter
import chn.phm.domain.model.setting.SettingData
import chn.phm.domain.repository.SettingRepository
import javax.inject.Inject

class SaveSettingUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) : SingleUseCaseWithParameter<SettingData, Unit> {

    override suspend fun execute(parameter: SettingData) {
        settingRepository.putSetting(parameter)
    }
}
