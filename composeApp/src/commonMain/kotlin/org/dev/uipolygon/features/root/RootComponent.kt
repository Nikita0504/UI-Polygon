package org.dev.uipolygon.features.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.dev.uipolygon.core.data.repository.ThemeRepositoryImpl
import org.dev.uipolygon.core.domain.models.ThemeSettings
import org.dev.uipolygon.core.domain.repository.ThemeRepository
import org.dev.uipolygon.features.code_view_page.CodeViewPageComponent
import org.dev.uipolygon.features.color_switcher_page.ColorSwitcherPageComponent
import org.dev.uipolygon.features.root.Root.Child

class RootComponent(
    componentContext: ComponentContext,
    settings: Settings,
) : Root, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()
    override val stack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.CodeViewPageContent,
        handleBackButton = true,
        childFactory = ::child,

    )

    val themeRepository: ThemeRepository = ThemeRepositoryImpl(settings = settings)

    private val scope = coroutineScope(Dispatchers.Main + SupervisorJob())

    override val themeSettings: StateFlow<ThemeSettings> = themeRepository.observeSettings()
        .stateIn(
            scope = scope,
            started = SharingStarted.Lazily,
            initialValue = ThemeSettings()
        )

    val allTabs = listOf(
        Config.CodeViewPageContent,
        Config.ColorSwitcherPageContent,
        Config.Test,
        Config.Test,
        Config.Test,
        Config.Test,



    )
    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.CodeViewPageContent -> Child.CodeViewPageContent(
                CodeViewPageComponent(componentContext)
            )
            is Config.ColorSwitcherPageContent -> Child.ColorSwitcherPageContent(
                ColorSwitcherPageComponent(
                    componentContext = componentContext,
                    themeRepository = themeRepository
                )
            )
            is Config.Test -> Child.Test(0)
        }

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    override fun navigate(config: Config) {
        navigation.bringToFront(config)
    }

}