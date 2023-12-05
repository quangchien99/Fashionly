package chn.phm.domain.model.setting

data class SettingData(
    val isFirstOpen: Boolean = true,
    val isNightMode: Boolean = false,
    val height: Int = 512,
    val width: Int = 384,
    val guidanceScale: Double = 8.0,
    val numInferenceSteps: Int = 20,
    val seed: Int = 128915590
)
