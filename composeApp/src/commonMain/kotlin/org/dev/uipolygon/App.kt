package org.dev.uipolygon

import androidx.compose.runtime.*
import com.arkivanov.decompose.ComponentContext
import com.russhwolf.settings.Settings
import org.dev.uipolygon.features.root.RootComponent
import org.dev.uipolygon.features.root.RootContent

@Composable
fun App(componentContext: ComponentContext, settings: Settings) {
    RootContent(
        component = RootComponent(componentContext = componentContext, settings = settings)
    )
}