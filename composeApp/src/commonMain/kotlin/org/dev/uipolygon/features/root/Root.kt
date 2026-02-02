package org.dev.uipolygon.features.root

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.dev.uipolygon.core.domain.models.ThemeSettings
import org.dev.uipolygon.features.code_view.CodeView
import org.dev.uipolygon.features.code_view_page.CodeViewPage
import org.dev.uipolygon.features.color_switcher_page.ColorSwitcherPage

interface Root {

    val stack: Value<ChildStack<*, Child>>
    val themeSettings: StateFlow<ThemeSettings>

    fun onBackClicked(toIndex: Int)
    fun navigate(config: Config)
    sealed class Child {
        class CodeViewPageContent(val component: CodeViewPage) : Child()

        class ColorSwitcherPageContent(val component: ColorSwitcherPage) : Child()

        class Test(val component: Any): Child()
    }

}