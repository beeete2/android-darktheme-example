package com.beeete2.android.examples.darktheme.domain.value

enum class SettingDarkTheme(val value: Int) {
    SYSTEM_DEFAULT(0),
    DAY(1),
    NIGHT(2);

    companion object {
        fun fromValue(value: Int): SettingDarkTheme = when (value) {
            0 -> SYSTEM_DEFAULT
            1 -> DAY
            2 -> NIGHT
            else -> SYSTEM_DEFAULT
        }
    }
}
