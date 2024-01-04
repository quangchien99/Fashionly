package chn.phm.domain.usecase.setting

import chn.phm.domain.base.SingleUseCase
import chn.phm.domain.repository.SettingRepository
import javax.inject.Inject

class UpdateIsFirstOpenUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) : SingleUseCase<Unit> {
    override suspend fun execute() {
        settingRepository.markOpened()
    }
}
