package org.dev.uipolygon.core.data.repository

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringFlow
import com.russhwolf.settings.serialization.decodeValue
import com.russhwolf.settings.serialization.encodeValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import org.dev.uipolygon.core.domain.models.AppResult

@OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
abstract class BaseSettingsRepository<T>(
    private val settings: Settings,
    private val key: String,
    private val serializer: KSerializer<T>,
    private val defaultValue: T
) {

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    fun get(): AppResult<T> {
        return try {
            val jsonString = settings.getString(key, "")
            if (jsonString.isBlank()) {
                AppResult.Success(defaultValue)
            } else {
                val decoded = json.decodeFromString(serializer, jsonString)
                AppResult.Success(decoded)
            }
        } catch (e: Exception) {
            AppResult.Error("Error decoding key $key", e)
        }
    }

    fun observe(): Flow<AppResult<T>> {
        val observableSettings = settings as? ObservableSettings
            ?: error("Settings must be observable...")

        return observableSettings.getStringFlow(key, "").map { jsonString ->
            println("DEBUG: Flow triggered for key '$key'. Value: '$jsonString'")

            if (jsonString.isBlank()) {
                AppResult.Success(defaultValue)
            } else {
                runCatching {
                    val decoded = json.decodeFromString(serializer, jsonString)
                    AppResult.Success(decoded)
                }.getOrElse {
                    println("DEBUG: Error decoding: $it")
                    AppResult.Error("Decoding error", it)
                }
            }
        }
    }


    fun save(value: T): AppResult<Unit> {
        return runCatching {
            val stringValue = json.encodeToString(serializer, value)
            settings.putString(key, stringValue)

            println("DEBUG: Saved value '$stringValue' for key '$key'")
            AppResult.Success(Unit)
        }.getOrElse {
            AppResult.Error("Error saving...", it)
        }
    }

    fun clear() {
        settings.remove(key)
    }
}