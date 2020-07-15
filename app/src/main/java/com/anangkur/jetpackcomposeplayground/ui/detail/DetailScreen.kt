package com.anangkur.jetpackcomposeplayground.ui.detail

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.height
import androidx.ui.layout.padding
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.res.vectorResource
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.anangkur.jetpackcomposeplayground.R
import com.anangkur.jetpackcomposeplayground.model.ListItem

@Composable
fun detailScreenContent(
    screenTitle: String,
    onNavigationClick: () -> Unit,
    data: ListItem
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = screenTitle) },
                navigationIcon = {
                    IconButton(onClick = onNavigationClick) {
                        Icon(asset = vectorResource(id = R.drawable.ic_baseline_arrow_back_24))
                    }
                }
            )
        },
        bodyContent = {
            VerticalScroller {
                Image(
                    asset = vectorResource(R.drawable.ic_baseline_adb_24),
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )
                Box(
                    modifier = Modifier.padding(20.dp),
                    children = {
                        Text(
                            text = data.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600
                        )
                        Text(
                            modifier = Modifier.padding(top = 10.dp),
                            text = data.desc,
                            fontSize = 14.sp
                        )
                    }
                )
            }
        }
    )
}

@Composable
@Preview
fun previewDetailScreenContent() {
    detailScreenContent(
        screenTitle = "Detail Page",
        onNavigationClick = {},
        data = ListItem(title = "test", image = "", desc = "test desc")
    )
}