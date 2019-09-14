package com.beeete2.android.examples.darktheme.presentation.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.beeete2.android.examples.darktheme.domain.entity.SettingsEntity
import com.beeete2.android.examples.darktheme.presentation.model.SettingsModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val settingsModel: SettingsModel
) : ViewModel() {

    private val _settings = settingsModel.settings
    val settings: LiveData<SettingsEntity> = _settings

}
