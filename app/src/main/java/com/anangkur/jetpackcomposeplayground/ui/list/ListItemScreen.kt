package com.anangkur.jetpackcomposeplayground.ui.list

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.layout.*
import androidx.ui.material.Card
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.anangkur.jetpackcomposeplayground.model.ListItem

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
            Text(
                text = data.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
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