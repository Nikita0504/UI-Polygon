package org.dev.uipolygon.core.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ThemeSettings(
    val isDarkMode: Boolean = true
)