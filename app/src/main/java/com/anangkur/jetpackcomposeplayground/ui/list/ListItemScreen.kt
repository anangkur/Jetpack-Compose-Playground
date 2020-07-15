package com.anangkur.jetpackcomposeplayground.ui.list

import android.graphics.Bitmap
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.*
import androidx.ui.material.Card
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.anangkur.jetpackcomposeplayground.model.ListItem
import com.anangkur.jetpackcomposeplayground.state.UiState
import com.anangkur.jetpackcomposeplayground.utils.loadPicture

@Composable
fun listItemScreenContent(
    data: ListItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(4.dp).clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Column( modifier = Modifier.padding(20.dp) ) {
            val loadPictureState = loadPicture(data.image)
            if (loadPictureState is UiState.Success<Bitmap>)
                Image(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentScale = ContentScale.Crop,
                    asset = loadPictureState.data.asImageAsset()
                )
            else
                Text(
                    modifier = Modifier.fillMaxWidth().height(200.dp).gravity(align = Alignment.CenterHorizontally),
                    text = "Loading..."
                )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = data.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = data.desc
            )
        }
    }
}

@Composable
@Preview
fun previewListItemScreenContent() {
    listItemScreenContent(
        data = ListItem(
            title = "Test",
            image = "",
            desc = "Test Desc"
        ),
        onClick = {  }
    )
}