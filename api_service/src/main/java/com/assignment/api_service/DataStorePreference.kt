package com.assignment.api_service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

@Singleton
class DataStorePreference @Inject constructor(@ApplicationContext val context: Context){
    private val dataStore: DataStore<Preferences> = context.dataStore

    private val TOKEN = stringPreferencesKey("token")
    private val USER = stringPreferencesKey("user")

    //Token data

    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN] ?: ""
        }
    }

    suspend fun getTokenString(): String {
        return dataStore.data.first()[TOKEN].orEmpty()
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    //User data

    fun getUsername(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USER] ?: ""
        }
    }

    suspend fun getUserString(): String {
        return dataStore.data.first()[USER].orEmpty()
    }

    suspend fun saveUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[USER] = username
        }
    }
}