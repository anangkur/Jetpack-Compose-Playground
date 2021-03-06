package com.anangkur.jetpackcomposeplayground.ui.detail

import androidx.compose.Composable
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.height
import androidx.ui.layout.padding
import androidx.ui.material.Scaffold
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.anangkur.jetpackcomposeplayground.model.ListItem
import com.anangkur.jetpackcomposeplayground.utils.topAppBarWithBack
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun detailScreenContent(
    screenTitle: String,
    onNavigationClick: () -> Unit,
    data: ListItem
) {
    Scaffold(
        topBar = {
            topAppBarWithBack(screenTitle = screenTitle, onNavigationClick = onNavigationClick)
        },
        bodyContent = {
            VerticalScroller {
                CoilImage(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    data = data.image,
                    contentScale = ContentScale.Crop
                )
                contentSection(title = data.title, desc = data.desc)
            }
        }
    )
}

@Composable
fun contentSection(title: String, desc: String) {
    Box(
        modifier = Modifier.padding(20.dp),
        children = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = desc,
                fontSize = 14.sp
            )
        }
    )
}

@Composable
@Preview
fun previewContentSection() {
    Scaffold {
        contentSection(title = "Test", desc = "Test Desc")
    }
}