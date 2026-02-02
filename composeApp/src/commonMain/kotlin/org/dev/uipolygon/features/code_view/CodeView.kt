package org.dev.uipolygon.features.code_view

import androidx.compose.ui.platform.ClipboardManager
import com.arkivanov.decompose.value.Value
import dev.snipme.highlights.Highlights

interface CodeView {

    val codeViewState: Value<CodeViewState>

    fun onToggleCodePanel()
    fun copyText(clipboardManager: ClipboardManager, text: String)
    fun choseFile(key: String)
}