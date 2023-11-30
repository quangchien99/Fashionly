package chn.phm.data.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

class DeviceInfoProvider(private val context: Context) {
    val androidId: String
        @SuppressLint("HardwareIds")
        get() = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}
