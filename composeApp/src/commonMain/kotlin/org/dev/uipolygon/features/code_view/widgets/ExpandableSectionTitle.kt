package org.dev.uipolygon.features.code_view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CopyAll
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.dev.uipolygon.core.common.icons.KotlinIcon

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ExpandableSectionTitle(
    isExpanded: Boolean,
    onClick: () -> Unit,
    copyText: () -> Unit,
    filesList: List<String>,
    choseFile: (file: String) -> Unit,
    selectedFile: String
) {

    val icon = if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown

    val containerColor = Color(57, 77, 110)
    val selectedTabColor = Color(7, 24, 51)
    val unselectedTabColor = Color.Transparent

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(containerColor)
            .height(40.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(filesList) { fileName ->
                val isSelected = fileName == selectedFile

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(if (isSelected) selectedTabColor else unselectedTabColor)
                        .clickable { choseFile(fileName) }
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(modifier = Modifier.fillMaxHeight(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Icon(
                            imageVector = KotlinIcon.Logo,
                            contentDescription = "Kotlin Logo",
                            modifier = Modifier.size(10.dp),
                            tint = Color.Unspecified
                        )
                        Box(
                            modifier = Modifier.size(4.dp)
                        )
                        Text(
                            text = fileName,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = copyText
            ) {
                Icon(
                    imageVector = Icons.Rounded.CopyAll,
                    contentDescription = "Copy",
                    tint = Color.White,
                )
            }
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Expand/Collapse",
                    tint = Color.White,
                )
            }
        }
    }
}