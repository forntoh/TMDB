package dev.forntoh.tmdb.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.forntoh.tmdb.ui.theme.TMDBTheme

@Composable
fun TMDBApp() {
    TMDBTheme {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState
        ) { innerPaddingModifier ->
            NavGraph(modifier = Modifier.padding(innerPaddingModifier))
        }
    }
}