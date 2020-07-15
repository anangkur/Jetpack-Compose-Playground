package com.anangkur.jetpackcomposeplayground.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.asImageAsset
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.height
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.anangkur.jetpackcomposeplayground.R
import com.anangkur.jetpackcomposeplayground.state.UiState
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun imageSection(imageUrl: String) {
    val loadPictureState = loadPicture(imageUrl)
    if (loadPictureState is UiState.Success<Bitmap>)
        imageComponent(imageAsset = loadPictureState.data.asImageAsset())
    else
        loadingComponent()
}

@Composable
fun loadPicture(url: String): UiState<Bitmap> {

    var bitmapState: UiState<Bitmap> by state<UiState<Bitmap>> { UiState.Loading }

    Glide.with(ContextAmbient.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState = UiState.Success(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) { }
        })

    return bitmapState
}

@Composable
fun imageComponent(imageAsset: ImageAsset) {
    Image(
        modifier = Modifier.fillMaxWidth().height(200.dp),
        contentScale = ContentScale.Crop,
        asset = imageAsset
    )
}

@Composable
fun loadingComponent() {
    Column(
        modifier = Modifier.fillMaxWidth().height(200.dp),
        verticalArrangement = Arrangement.Center,
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading...")
    }
}

@Composable
@Preview
fun previewImageComponent() {
    imageComponent(imageAsset = imageFromResource(ContextAmbient.current.resources, R.drawable.dummy_image))
}

@Composable
@Preview
fun previewLoadingComponent() {
    loadingComponent()
}