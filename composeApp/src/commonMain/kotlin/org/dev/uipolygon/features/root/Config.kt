package org.dev.uipolygon.features.root

import kotlinx.serialization.Serializable
@Serializable
sealed interface Config {
    @Serializable
    data object CodeViewPageContent : Config

    @Serializable
    data object ColorSwitcherPageContent : Config

    @Serializable
    data object Test : Config
}