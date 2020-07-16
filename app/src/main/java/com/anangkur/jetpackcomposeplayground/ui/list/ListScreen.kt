package com.anangkur.jetpackcomposeplayground.ui.list

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.layout.*
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Button
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.Scaffold
import androidx.ui.material.Surface
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.anangkur.jetpackcomposeplayground.model.ListItem
import com.anangkur.jetpackcomposeplayground.model.Result
import com.anangkur.jetpackcomposeplayground.model.error
import com.anangkur.jetpackcomposeplayground.model.successOr
import com.anangkur.jetpackcomposeplayground.state.*
import com.anangkur.jetpackcomposeplayground.utils.SwipeToRefreshLayout
import com.anangkur.jetpackcomposeplayground.utils.topAppBar

@Composable
fun liveDataComponent(
    appTitle: String,
    viewModel: ListViewModel,
    onClick: (ListItem) -> Unit
) {
    val result by viewModel.news.observeAsState(Result.Loading)

    if (result is Result.Loading) {
        liveDataLoadingComponent()
    } else {
        Stack(modifier = Modifier.fillMaxSize()) {
            val data = result.successOr(emptyList())
            if (data.isNotEmpty()) {
                listScreenContent(
                    appTitle = appTitle,
                    data = data,
                    onClick = onClick
                )
            } else {
                errorComponent(errorMessage = result.error().message, onClick = { viewModel.getNews() })
            }
        }
    }
}

@Composable
fun refreshableStateComponent(
    appTitle: String,
    viewModel: ListViewModel,
    onClick: (ListItem) -> Unit
) {
    val (state, refresh) = refreshableUiStateFrom(repositoryCall = viewModel::getNews)

    if (state.loading && !state.refreshing) {
        liveDataLoadingComponent()
    } else {
        SwipeToRefreshLayout(
            refreshingState = state.refreshing,
            onRefresh = { refresh() },
            refreshIndicator = {
                Surface(elevation = 10.dp, shape = CircleShape) {
                    CircularProgressIndicator(Modifier.preferredSize(50.dp).padding(4.dp))
                }
            }
        ) {
            Stack(modifier = Modifier.fillMaxSize()) {
                errorComponent(errorMessage = state.error?.message, onClick = { refresh() })
                state.currentData?.let { posts ->
                    listScreenContent(appTitle = appTitle, data = posts, onClick = onClick)
                }
            }
        }
    }
}

@Composable
fun listScreenContent(
    appTitle: String,
    data: List<ListItem>,
    onClick: (ListItem) -> Unit
) {
    Scaffold(
        topBar = { topAppBar(screenTitle = appTitle) },
        bodyContent = { bodyListScreen(data = data, onClick = onClick) }
    )
}

@Composable
fun bodyListScreen(data: List<ListItem>, onClick: (ListItem) -> Unit) {
    VerticalScroller {
        data.forEachIndexed { index, listItem ->
            listItemScreenContent(
                data = listItem,
                onClick = { onClick(listItem) }
            )
        }
    }
}

@Composable
@Preview
fun liveDataLoadingComponent() {
    Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}

@Composable
fun errorComponent(
    errorMessage: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage ?: "Can't update latest news",
            modifier = Modifier.padding(horizontal = 20.dp),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onClick,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(text = "RETRY")
        }
    }
}

@Composable
@Preview
fun previewErrorComponent() {
    errorComponent(errorMessage = null, onClick = {})
}