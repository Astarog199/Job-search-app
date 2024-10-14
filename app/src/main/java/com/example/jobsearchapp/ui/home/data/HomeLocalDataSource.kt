package com.example.jobsearchapp.ui.home.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.jobsearchapp.ui.home.data.models.OffersDataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor (private val dataStore: DataStore<Preferences>) {
    fun consume() : Flow<List<OffersDataEntity>> = dataStore.data
        .map(::mapProductFromPrefs)

    suspend fun save(coins: List<OffersDataEntity>) {
        dataStore.edit { prefs -> prefs[productPreferencesKey] = encodeToString(coins) }
    }

    @OptIn(InternalSerializationApi::class)
    private fun decodeFromString(string: String): List<OffersDataEntity> =
        try {
            Json.decodeFromString(ListSerializer(OffersDataEntity::class.serializer()), string)
        } catch (e: Exception) {
            listOf()
        }

    private fun mapProductFromPrefs(prefs: Preferences): List<OffersDataEntity> =
        prefs[productPreferencesKey]
            ?.takeIf(String::isNotEmpty)
            ?.let(this::decodeFromString) ?: listOf()

    private val productPreferencesKey = stringPreferencesKey(PRODUCT_KEY)

    @OptIn(InternalSerializationApi::class)
    private fun encodeToString(products: List<OffersDataEntity>): String =
        Json.encodeToString(
            ListSerializer(OffersDataEntity::class.serializer()),
            (products),
        )

    private companion object {
        const val PRODUCT_KEY = "layered_product_key"
    }
}