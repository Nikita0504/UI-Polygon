package org.dev.uipolygon.core.data.repository

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.dev.uipolygon.core.domain.models.AppResult
import org.dev.uipolygon.core.domain.models.ThemeSettings
import org.dev.uipolygon.core.domain.repository.ThemeRepository


class ThemeRepositoryImpl(settings: Settings) : ThemeRepository,  BaseSettingsRepository<ThemeSettings>(
    settings = settings ,
    key =  "isDarkMode",
    serializer =  ThemeSettings.serializer(),
    defaultValue = ThemeSettings()
){
    override fun getSettings() = get()
    override fun saveSettings(settings: ThemeSettings) = save(settings)
    override fun observeSettings(): Flow<ThemeSettings> {
        val flow = observe()

        return flow.map { result ->
            when (result) {
                is AppResult.Success -> result.data
                is AppResult.Error -> ThemeSettings()
            }
        }
    }
}