package com.beeete2.android.examples.darktheme


import androidx.appcompat.app.AppCompatDelegate
import com.beeete2.android.examples.darktheme.di.DaggerAppComponent
import com.beeete2.android.examples.darktheme.presentation.model.SettingsModel
import com.beeete2.android.examples.darktheme.presentation.ui.toNightMode
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject
    lateinit var settingsModel: SettingsModel

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(settingsModel.darkTheme.toNightMode())
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
        .factory()
        .create(this)
}
