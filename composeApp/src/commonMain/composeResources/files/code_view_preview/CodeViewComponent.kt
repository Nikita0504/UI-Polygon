class CodeViewComponent(
    componentContext: ComponentContext,
    val previewKey: String
) : CodeView ,ComponentContext by componentContext {

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