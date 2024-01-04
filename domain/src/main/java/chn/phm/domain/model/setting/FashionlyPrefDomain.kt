package chn.phm.domain.model.setting

data class FashionlyPrefDomain(
    val isFirstOpen: Boolean = true,
    val isEnableDarkMode: Boolean = false,
    val fashionlySettings: FashionlySettingsDomain = FashionlySettingsDomain()
) {
    data class FashionlySettingsDomain(
        val negativePrompt: String = "Low quality, unrealistic, bad cloth, warped cloth",
        val height: Int = 512,
        val width: Int = 384,
        val guidanceScale: Double = 8.0,
        val numInferenceSteps: Int = 20,
        val seed: Int = 128915590
    )
}
