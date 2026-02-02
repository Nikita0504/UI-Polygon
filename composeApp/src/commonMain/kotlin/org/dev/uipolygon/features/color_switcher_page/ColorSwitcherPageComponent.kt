package org.dev.uipolygon.features.color_switcher_page

import com.arkivanov.decompose.ComponentContext
import org.dev.uipolygon.core.domain.models.AppResult
import org.dev.uipolygon.core.domain.repository.ThemeRepository
import org.dev.uipolygon.features.code_view.CodeView
import org.dev.uipolygon.features.code_view.CodeViewComponent

class ColorSwitcherPageComponent(
    val themeRepository: ThemeRepository,
    componentContext: ComponentContext,
): ColorSwitcherPage, ComponentContext by componentContext {

    override val colorSwitcherCodeView: CodeView = CodeViewComponent(previewKey = "ColorSwitcherCodeView", componentContext = componentContext)

    override fun changeTheme() {
        val result = themeRepository.getSettings()

        if (result is AppResult.Success) {
            val currentSettings = result.data

            val newSettings = currentSettings.copy(
                isDarkMode = !currentSettings.isDarkMode
            )

            themeRepository.saveSettings(newSettings)

        }else{
            println(result.toString())
        }
    }
}