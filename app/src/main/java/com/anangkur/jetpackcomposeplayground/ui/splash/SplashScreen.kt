package com.anangkur.jetpackcomposeplayground.ui.splash

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.imageFromResource
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
import androidx.ui.material.Scaffold
import androidx.ui.res.vectorResource
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.anangkur.jetpackcomposeplayground.R

@Composable
fun splashScreenContent(
    appTitle: String,
    imageAsset: VectorAsset
) {
    Scaffold(
        bodyContent = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalGravity = Alignment.CenterHorizontally
            ) {
                Image(asset = imageAsset)
                Text(
                    modifier = Modifier.padding(start = 80.dp, end = 80.dp),
                    text = appTitle,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W600
                )
            }
        }
    )
}

@Composable
@Preview
fun previewSplashContent() {
    splashScreenContent(
        appTitle = ContextAmbient.current.getString(R.string.app_name),
        imageAsset = vectorResource(id = R.drawable.ic_baseline_android_24)
    )
}