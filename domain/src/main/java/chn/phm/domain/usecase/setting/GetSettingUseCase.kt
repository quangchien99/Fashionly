package chn.phm.domain.usecase.setting

import chn.phm.domain.base.SingleUseCase
import chn.phm.domain.model.setting.SettingData
import chn.phm.domain.repository.SettingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) : SingleUseCase<Flow<SettingData>> {

    override suspend fun execute(): Flow<SettingData> {
        return settingRepository.getSettingData()
    }
}
