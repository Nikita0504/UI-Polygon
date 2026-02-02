package org.dev.uipolygon.features.root.widgets


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.dev.uipolygon.features.root.Config

@Composable
fun SandboxBottomBar(
    onTabClicked: (Config) -> Unit,
    currentConfig: Config?,
    modifier: Modifier = Modifier,
    tabs: List<Config>
) {
    Surface(
        color = NavigationBarDefaults.containerColor,
        tonalElevation = NavigationBarDefaults.Elevation,
        modifier = modifier.fillMaxWidth()
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val itemWidth = maxWidth / 3

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                items(tabs.size) { index ->
                    val isSelected = currentConfig == tabs[index]

                    Box(modifier = Modifier.width(itemWidth)) {
                        Row {
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = { onTabClicked(tabs[index]) },
                                icon = {
                                    Icon(
                                        imageVector = androidx.compose.material.icons.Icons.Default.Layers,
                                        contentDescription = null,
                                    )
                                },
                                label = {
                                    Text(
                                        text = tabs[index]::class.simpleName ?: "",
                                        maxLines = 1
                                    )
                                },
                                alwaysShowLabel = true
                            )
                        }
                    }
                }
            }
        }
    }
}