package com.anangkur.jetpackcomposeplayground.ui.style

import androidx.compose.Composable
import androidx.ui.graphics.Color
import androidx.ui.material.ColorPalette

@Composable
val ColorPalette.snackbarAction: Color
    get() = if (isLight) Red300 else Red700