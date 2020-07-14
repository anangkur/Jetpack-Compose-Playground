package com.anangkur.jetpackcomposeplayground.ui.splash

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.material.Scaffold
import androidx.ui.tooling.preview.Preview

@Composable
@Preview
fun splashScreenContent() {
    Scaffold(
        bodyContent = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalGravity = Alignment.CenterHorizontally
            ) {
                Text(text = "test")
            }
        }
    )
}