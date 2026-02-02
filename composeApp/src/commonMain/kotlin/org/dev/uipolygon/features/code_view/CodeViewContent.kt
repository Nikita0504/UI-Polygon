package org.dev.uipolygon.features.code_view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import dev.snipme.kodeview.view.CodeTextView
import org.dev.uipolygon.features.code_view.widgets.ExpandableSectionTitle

@Composable
fun CodeViewContent(component: CodeView, modifier: Modifier? = null) {

    val state by component.codeViewState.subscribeAsState()
    val clipboardManager = LocalClipboardManager.current

    Column(
        modifier = (modifier ?: Modifier).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        ExpandableSectionTitle(
            isExpanded = state.isOpen,
            onClick = { component.onToggleCodePanel() },
            copyText = { component.copyText(clipboardManager, state.text) },
            filesList = state.filesList,
            choseFile = {key -> component.choseFile(key)},
            selectedFile = state.selectedFile ?: "non"
        )


        AnimatedVisibility(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                .background(Color(7, 24, 51))
                .fillMaxWidth(),
            visible = state.isOpen
        ) {
            ProvideTextStyle(
                value = TextStyle(
                    color = Color(209, 218, 232),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp
                )
            ) {
                state.highlights?.let {
                    CodeTextView(
                        highlights = it,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }


    }
}