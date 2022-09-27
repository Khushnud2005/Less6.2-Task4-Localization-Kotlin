package uz.exemple.less62_task4_localization_kotlin

import android.app.Application
import android.content.res.Configuration
import uz.exemple.less62_task4_localization_kotlin.managers.LocaleManager


class MyApplication : Application() {
    companion object {
        lateinit var localManager: LocaleManager
    }

    override fun onCreate() {
        super.onCreate()
        localManager = LocaleManager(this)
        localManager.setLocale(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localManager.setLocale(this)
    }


}