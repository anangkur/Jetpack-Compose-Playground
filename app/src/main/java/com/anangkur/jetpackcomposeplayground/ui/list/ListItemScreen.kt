package com.anangkur.jetpackcomposeplayground.ui.list

import androidx.compose.Composable
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.height
import androidx.ui.layout.padding
import androidx.ui.material.Card
import androidx.ui.material.Scaffold
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.anangkur.jetpackcomposeplayground.model.ListItem
import com.anangkur.jetpackcomposeplayground.utils.imageSection
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun listItemScreenContent(
    data: ListItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(4.dp).clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Column {
            CoilImage(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                data = data.image,
                contentScale = ContentScale.Crop
            )
            bodyItemSection(title = data.title, desc = data.desc)
        }
    }
}

@Composable
fun bodyItemSection(title: String, desc: String) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.W600
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = desc
        )
    }
}

@Composable
@Preview
fun previewListItemScreenContent() {
    Scaffold {
        bodyItemSection(title = "Test", desc = "Test Desc")
    }
}