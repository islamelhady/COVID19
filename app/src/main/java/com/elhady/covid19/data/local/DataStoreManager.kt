package com.elhady.covid19.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.elhady.covid19.utils.Constants
import com.elhady.covid19.utils.Constants.PREF_NEWS_TYPE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore(Constants.PREFERENCES_NAME)

class DataStoreManager(appContext: Context) {

    private val dataStore = appContext.dataStore

    suspend fun toggleButton(isChecked: Boolean) {
        dataStore.edit { preference ->
            preference[PREF_NEWS_TYPE] = isChecked
        }
    }

    val readToggleButton: Flow<Boolean?> = dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preference ->
            preference[PREF_NEWS_TYPE] ?: true
        }
}