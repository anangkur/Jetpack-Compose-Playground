package com.anangkur.jetpackcomposeplayground.ui.list

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.launchInComposition
import androidx.compose.stateFor
import androidx.lifecycle.LiveData
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.layout.*
import androidx.ui.livedata.observeAsState
import androidx.ui.material.*
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.anangkur.jetpackcomposeplayground.model.ListItem
import com.anangkur.jetpackcomposeplayground.state.*
import com.anangkur.jetpackcomposeplayground.ui.style.snackbarAction
import com.anangkur.jetpackcomposeplayground.utils.SwipeToRefreshLayout
import com.anangkur.jetpackcomposeplayground.utils.topAppBar
import kotlinx.coroutines.delay

@Composable
fun liveDataComponent(
    appTitle: String,
    listItemLiveData: LiveData<List<ListItem>>,
    onClick: (ListItem) -> Unit
) {
    val listItem by listItemLiveData.observeAsState(initial = emptyList())

    if (listItem.isEmpty()) {
        liveDataLoadingComponent()
    } else {
        listScreenContent(appTitle = appTitle, data = listItem, onClick = onClick)
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
            val (showSnackbarError, updateShowSnackbarError) = stateFor(state) {
                state is RefreshableUiState.Error
            }
            Stack(modifier = Modifier.fillMaxSize()) {
                state.currentData?.let { posts ->
                    listScreenContent(appTitle = appTitle, data = posts, onClick = onClick)
                }
                ErrorSnackbar(
                    showError = showSnackbarError,
                    onErrorAction = { refresh() },
                    onDismiss = { updateShowSnackbarError(false) },
                    modifier = Modifier.gravity(Alignment.BottomCenter)
                )
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
fun ErrorSnackbar(
        showError: Boolean,
        modifier: Modifier = Modifier,
        onErrorAction: () -> Unit = { },
        onDismiss: () -> Unit = { }
) {
    if (showError) {
        launchInComposition {
            delay(timeMillis = 5000L)
            onDismiss()
        }

        Snackbar(
            modifier = modifier.padding(16.dp),
            text = { Text("Can't update latest news") },
            action = {
                TextButton(
                    onClick = {
                        onErrorAction()
                        onDismiss()
                    },
                    contentColor = contentColor()
                ) {
                    Text(
                        text = "RETRY",
                        color = MaterialTheme.colors.snackbarAction
                    )
                }
            }
        )
    }
}