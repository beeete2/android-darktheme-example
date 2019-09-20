package com.beeete2.android.examples.darktheme.presentation.ui.settings.darktheme

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.beeete2.android.examples.darktheme.domain.entity.SettingsEntity
import com.beeete2.android.examples.darktheme.presentation.model.SettingsModel
import com.beeete2.android.examples.darktheme.domain.value.SettingDarkTheme
import javax.inject.Inject

class DarkThemeViewModel @Inject constructor(
    private val settingsModel: SettingsModel
) : ViewModel() {

    val settings: LiveData<SettingsEntity> = settingsModel.settings

    fun saveDarkTheme(darkTheme: SettingDarkTheme) {
        settingsModel.darkTheme = darkTheme
    }

}
