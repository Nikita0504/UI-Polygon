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