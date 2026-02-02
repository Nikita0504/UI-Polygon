package org.dev.uipolygon.core.domain.repository

import kotlinx.coroutines.flow.Flow
import org.dev.uipolygon.core.domain.models.AppResult
import org.dev.uipolygon.core.domain.models.ThemeSettings

interface ThemeRepository{

    fun getSettings(): AppResult<ThemeSettings>
    fun saveSettings(settings: ThemeSettings): AppResult<Unit>
    fun observeSettings(): Flow<ThemeSettings>

}