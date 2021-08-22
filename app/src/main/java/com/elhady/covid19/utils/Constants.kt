package com.elhady.covid19.utils

import androidx.datastore.preferences.core.booleanPreferencesKey

object Constants {

    const val PREFERENCES_NAME: String = "COVID_PREFERENCES"
    val PREF_NEWS_TYPE = booleanPreferencesKey("pref_news_type")
}