package org.dev.uipolygon.features.code_view_page

import com.arkivanov.decompose.ComponentContext
import org.dev.uipolygon.features.code_view.CodeViewComponent

class CodeViewPageComponent(
    componentContext: ComponentContext,
    ) : CodeViewPage {

    override val codeView = CodeViewComponent(previewKey = "CodeViewPreview", componentContext = componentContext)

}