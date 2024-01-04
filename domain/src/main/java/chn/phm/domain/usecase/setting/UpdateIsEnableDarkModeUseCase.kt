package chn.phm.domain.usecase.setting

import chn.phm.domain.base.SingleUseCaseWithParameter
import chn.phm.domain.repository.SettingRepository
import javax.inject.Inject

class UpdateIsEnableDarkModeUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) : SingleUseCaseWithParameter<Boolean, Unit> {

    override suspend fun execute(parameter: Boolean) {
        settingRepository.setIsEnableDarkMode(parameter)
    }
}
