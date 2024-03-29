package com.beeete2.android.examples.darktheme.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beeete2.android.examples.darktheme.data.repository.SettingsRepository
import com.beeete2.android.examples.darktheme.domain.entity.SettingsEntity
import com.beeete2.android.examples.darktheme.domain.value.SettingDarkTheme
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    private val _settings = MutableLiveData<SettingsEntity>().apply {
        value = load()
    }
    val settings: LiveData<SettingsEntity> = _settings

    var darkTheme: SettingDarkTheme
        get() = SettingDarkTheme.fromValue(settingsRepository.readDarkTheme())
        set(value) {
            settingsRepository.writeDarkTheme(value.value)
            _settings.value = _settings.value?.copy(darkTheme = value)
        }

    private fun load(): SettingsEntity = SettingsEntity(
        darkTheme = darkTheme
    )

}
