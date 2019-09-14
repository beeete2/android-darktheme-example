package com.beeete2.android.examples.darktheme.data.repository

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SettingsRepository(context: Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun readDarkTheme(): Int = sharedPreferences.getInt(Key.DARK_THEME.name, 0)

    fun writeDarkTheme(value: Int) {
        sharedPreferences.edit {
            putInt(Key.DARK_THEME.name, value)
        }
    }

    enum class Key {
        DARK_THEME
    }

}
