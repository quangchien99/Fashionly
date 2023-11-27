package chn.phm.fashionly.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FashionlyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
