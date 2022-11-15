package dev.forntoh.tmdb.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

/**
 * SearchTextField
 * */
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    placeHolder: String,
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onQueryChanged: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var showClearButton by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> showClearButton = focusState.isFocused },
        value = query,
        onValueChange = {
            query = it
            onQueryChanged(it)
        },
        placeholder = { Text(text = placeHolder) },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        trailingIcon = {
            if (showClearButton) {
                IconButton(onClick = {
                    query = ""
                    focusManager.clearFocus()
                    onClearClick()
                }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear")
                }
            }
        },
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            onDoneActionClick()
        }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        )
    )
}