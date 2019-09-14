package com.beeete2.android.examples.darktheme.presentation.ui

import com.beeete2.android.examples.darktheme.R
import com.beeete2.android.examples.darktheme.domain.value.SettingDarkTheme

internal fun SettingDarkTheme.toStringRes() = when (this) {
    SettingDarkTheme.SYSTEM_DEFAULT -> R.string.item_dark_theme_system_default
    SettingDarkTheme.DAY -> R.string.item_dark_theme_day
    SettingDarkTheme.NIGHT -> R.string.item_dark_theme_night
}
