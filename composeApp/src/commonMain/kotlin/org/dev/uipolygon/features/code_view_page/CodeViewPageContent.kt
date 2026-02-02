package org.dev.uipolygon.features.code_view_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.dev.uipolygon.features.code_view.CodeViewContent

@Composable
fun CodeViewPageContent(component: CodeViewPage) {

    Column(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CodeViewContent(component = component.codeView, modifier = Modifier.padding(5.dp))

    }

}