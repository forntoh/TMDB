package dev.forntoh.tmdb.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AutoCompleteTextField(
    modifier: Modifier = Modifier,
    initialText: String = "",
    itemList: List<String>,
    onQuery: (String) -> Unit,
    onClearResults: () -> Unit,
) {
    var field by remember {
        mutableStateOf(TextFieldValue(text = initialText))
    }

    LaunchedEffect(key1 = field.text) {
        if (field.text.isNotBlank()) {
            delay(1000L)
            onQuery(field.text.trim())
        }
    }
    LazyColumn(modifier = modifier.animateContentSize()) {
        item {
            TextField(
                value = field,
                onValueChange = {
                    field = it
                    onClearResults()
                },
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Search")
                }
            )
        }
        if (itemList.isNotEmpty() && field.text.isNotBlank()) {
            items(itemList) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable(onClick = {
                            field = TextFieldValue(item)
                        })
                )
            }
        }
    }
}