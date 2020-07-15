package com.anangkur.jetpackcomposeplayground.ui.list

import androidx.compose.Composable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.material.Scaffold
import com.anangkur.jetpackcomposeplayground.model.ListItem
import com.anangkur.jetpackcomposeplayground.utils.topAppBar

@Composable
fun listScreenContent(
    appTitle: String,
    data: List<ListItem>,
    onClick: (ListItem) -> Unit
) {
    Scaffold(
        topBar = { topAppBar(screenTitle = appTitle) },
        bodyContent = { bodyListScreen(data = data, onClick = onClick) }
    )
}

@Composable
fun bodyListScreen(data: List<ListItem>, onClick: (ListItem) -> Unit) {
    VerticalScroller {
        data.forEachIndexed { index, listItem ->
            listItemScreenContent(
                data = listItem,
                onClick = { onClick(listItem) }
            )
        }
    }
}