package com.beeete2.android.examples.darktheme.presentation.ui

import androidx.appcompat.app.AppCompatDelegate
import com.beeete2.android.examples.darktheme.R
import com.beeete2.android.examples.darktheme.domain.value.SettingDarkTheme

internal fun SettingDarkTheme.toStringRes() = when (this) {
    SettingDarkTheme.SYSTEM_DEFAULT -> R.string.item_dark_theme_system_default
    SettingDarkTheme.DAY -> R.string.item_dark_theme_day
    SettingDarkTheme.NIGHT -> R.string.item_dark_theme_night
}

internal fun SettingDarkTheme.toNightMode() = when (this) {
    SettingDarkTheme.SYSTEM_DEFAULT -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    SettingDarkTheme.DAY -> AppCompatDelegate.MODE_NIGHT_NO
    SettingDarkTheme.NIGHT -> AppCompatDelegate.MODE_NIGHT_YES
}
