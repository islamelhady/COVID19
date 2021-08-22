package com.elhady.covid19.ui.settings

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.elhady.covid19.R
import com.elhady.covid19.utils.SettingsEvent

class SettingsViewModel(private val context: Context) : ViewModel() {

    val mode = ObservableInt(Int.MIN_VALUE)

    fun enableNotifications(enabled: Boolean) {
        val variant = if (enabled) {
            SettingsEvent.ChoiceVariant.ON
        } else {
            SettingsEvent.ChoiceVariant.OFF
        }
        SettingsEvent("Notifications",variant)
    }

    fun changeTheme(theme: String) {
        val newMode = when (theme) {
            context.getString(R.string.theme_value_dark) -> AppCompatDelegate.MODE_NIGHT_YES
            context.getString(R.string.theme_value_light) -> AppCompatDelegate.MODE_NIGHT_NO
            context.getString(R.string.theme_value_default) -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            else -> throw IllegalArgumentException("Mode with value: $theme is not supported")
        }
        mode.set(newMode)
        SettingsEvent(theme, SettingsEvent.ListVariant.THEME)
    }

    fun changeNotificationInterval(interval: String) {
        SettingsEvent(
            "$interval minutes",
            SettingsEvent.ListVariant.FREQUENCY
        )
    }
}
