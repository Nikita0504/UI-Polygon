package org.dev.uipolygon.features.code_view

import dev.snipme.highlights.Highlights

data class CodeViewState(
    val text: String = "",
    val isOpen: Boolean = false,
    val filesList: List<String> = emptyList(),
    val selectedFile: String? = null,
    val highlights: Highlights? = null
)
