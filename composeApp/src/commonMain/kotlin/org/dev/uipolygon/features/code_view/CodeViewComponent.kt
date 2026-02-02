package org.dev.uipolygon.features.code_view

import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.buildAnnotatedString
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxThemes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.dev.uipolygon.core.common.codeBlocks
import uipolygon.composeapp.generated.resources.Res


class CodeViewComponent(
    componentContext: ComponentContext,
    val previewKey: String
) : CodeView, ComponentContext by componentContext {

    private val _codeViewState = MutableValue(CodeViewState())
    override val codeViewState: Value<CodeViewState> = _codeViewState

    private val scope = coroutineScope(Dispatchers.Main + SupervisorJob())
    init {
        loadFilesKeys()
        loadFileContent()
    }

    private fun loadFilesKeys(){
        val filesPath = codeBlocks[previewKey]
        val filesList = filesPath?.keys?.toList() ?: listOf("Empty")
        _codeViewState.update {
            it.copy(filesList = filesList, selectedFile = filesList[0])
        }
    }

    override fun choseFile(key: String){
        _codeViewState.update {
            it.copy(selectedFile = key)
        }
        loadFileContent()
    }

    private fun updateHighlights(){
        _codeViewState.update {
            it.copy(
                highlights = textFormatting(it.text)
            )
        }
    }

    private fun loadFileContent() {
        scope.launch {
            val selectedFile = _codeViewState.value.selectedFile
            if (selectedFile != null) {
                val fullPath = codeBlocks[previewKey]?.get(selectedFile)
                if(fullPath != null){
                    val bytes = Res.readBytes(fullPath)
                    val text = bytes.decodeToString()
                    _codeViewState.update {
                        it.copy(
                            text = text
                        )
                    }
                    updateHighlights()
                }else{
                    _codeViewState.update {
                        it.copy(
                            text = "Couldn't find the selected file"
                        )
                    }
                }
            }else{
                _codeViewState.update {
                    it.copy(
                        text = "Couldn't find the selected file"
                    )
                }
            }
        }
    }

    override fun onToggleCodePanel() {
        _codeViewState.update {
            it.copy(isOpen = !it.isOpen)
        }
    }

    override fun copyText(clipboardManager: ClipboardManager, text: String) {
        clipboardManager.setText(
            annotatedString = buildAnnotatedString {
                append(text = text)
            }
        )    }

    private fun textFormatting(text: String): Highlights {
        return Highlights.Builder()
            .code(text)
            .theme(SyntaxThemes.pastel())
            .language(SyntaxLanguage.KOTLIN)
            .build()
    }
}