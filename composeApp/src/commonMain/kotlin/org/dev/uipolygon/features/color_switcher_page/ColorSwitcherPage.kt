package org.dev.uipolygon.features.color_switcher_page

import org.dev.uipolygon.features.code_view.CodeView

interface ColorSwitcherPage {

    val colorSwitcherCodeView: CodeView

    fun changeTheme()

}
