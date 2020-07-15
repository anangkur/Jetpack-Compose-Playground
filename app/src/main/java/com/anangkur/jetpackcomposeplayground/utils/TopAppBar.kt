package com.anangkur.jetpackcomposeplayground.utils

import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.material.IconButton
import androidx.ui.material.TopAppBar
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.anangkur.jetpackcomposeplayground.R

@Composable
fun topAppBarWithBack(
    screenTitle: String,
    onNavigationClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = screenTitle) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(asset = vectorResource(id = R.drawable.ic_baseline_arrow_back_24))
            }
        }
    )
}

@Composable
fun topAppBar(
    screenTitle: String
) {
    TopAppBar(
        title = { Text(text = screenTitle) }
    )
}

@Composable
@Preview
fun previewTopAppBarWithBack() {
    topAppBarWithBack(
        screenTitle = ContextAmbient.current.getString(R.string.app_name),
        onNavigationClick = {}
    )
}

@Composable
@Preview
fun previewTopAppbar() {
    topAppBar(screenTitle = ContextAmbient.current.getString(R.string.app_name))
}