package dev.forntoh.tmdb.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> AutoCompleteTextField(
    placeHolder: String,
    predictions: List<T>,
    modifier: Modifier = Modifier,
    onQueryChanged: (String) -> Unit = {},
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onItemClick: (T) -> Unit = {},
    itemContent: @Composable (T) -> Unit = {}
) {

    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        modifier = modifier.animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            SearchTextField(
                placeHolder = placeHolder,
                onQueryChanged = onQueryChanged,
                onDoneActionClick = onDoneActionClick,
                onClearClick = onClearClick
            )
        }
        if (predictions.isNotEmpty()) {
            items(predictions) { prediction ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(prediction)
                        }
                ) {
                    itemContent(prediction)
                }
            }
        }
    }
}
