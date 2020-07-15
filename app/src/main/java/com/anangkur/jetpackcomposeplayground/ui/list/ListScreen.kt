package com.anangkur.jetpackcomposeplayground.ui.list

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import com.anangkur.jetpackcomposeplayground.model.ListItem

@Composable
fun listScreenContent(
    appTitle: String,
    data: List<ListItem>,
    onClick: (ListItem) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = appTitle) }) },
        bodyContent = {
            VerticalScroller {
                data.forEachIndexed { index, listItem ->
                    listItemScreenContent(
                        data = listItem,
                        onClick = { onClick(listItem) }
                    )
                }
            }
        }
    )
}

@Composable
@Preview
fun previewListScreenContent() {
    listScreenContent(
        appTitle = "Test",
        data = listOf(
            ListItem(title = "Test 1", desc = "Desc Test 1", image = ""),
            ListItem(title = "Test 2", desc = "Desc Test 2", image = ""),
            ListItem(title = "Test 3", desc = "Desc Test 3", image = ""),
            ListItem(title = "Test 4", desc = "Desc Test 4", image = ""),
            ListItem(title = "Test 5", desc = "Desc Test 5", image = "")
        ),
        onClick = {  }
    )
}