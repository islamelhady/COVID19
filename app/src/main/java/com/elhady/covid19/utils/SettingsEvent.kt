package com.elhady.covid19.utils

class SettingsEvent<T,B>(titleName: T, clickType: B) {

    enum class ChoiceVariant(val variantName: String) {
        ON("On"),
        OFF("Off")
    }
    enum class ListVariant(val variantName: String) {
        THEME("Theme"),
        FREQUENCY("Notification Frequency")
    }
}
