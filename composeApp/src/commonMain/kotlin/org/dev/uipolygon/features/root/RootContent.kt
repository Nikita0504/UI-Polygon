package org.dev.uipolygon.features.root

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.dev.uipolygon.core.common.theme.AppTheme
import org.dev.uipolygon.features.code_view_page.CodeViewPageContent
import org.dev.uipolygon.features.color_switcher_page.ColorSwitcherPageContent
import org.dev.uipolygon.features.root.widgets.SandboxBottomBar


@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {

    val stack by component.stack.subscribeAsState()
    val activeConfig = stack.active.configuration

    val themeSettings by component.themeSettings.collectAsState()

    AppTheme(isDarkTheme = themeSettings.isDarkMode){
        Scaffold(
            modifier = modifier.fillMaxSize(),
            bottomBar = {
                SandboxBottomBar(
                    onTabClicked = component::navigate,
                    currentConfig = activeConfig as Config?,
                    tabs = component.allTabs
                )
            }
        ) { innerPadding ->
            Children(
                stack = component.stack,
                modifier = Modifier.padding(innerPadding),
                animation = stackAnimation(fade(tween(300))),
            ) {
                when (val child = it.instance) {
                    is Root.Child.CodeViewPageContent -> CodeViewPageContent(component = child.component)
                    is Root.Child.ColorSwitcherPageContent -> ColorSwitcherPageContent(component = child.component)
                    is Root.Child.Test -> Text("Test")
                }
            }
        }
    }
}