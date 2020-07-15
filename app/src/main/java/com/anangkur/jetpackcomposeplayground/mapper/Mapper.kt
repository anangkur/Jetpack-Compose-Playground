package com.anangkur.jetpackcomposeplayground.mapper

interface Mapper<UI_MODEL, MODEL> {
    fun mapToUiModel(data: MODEL): UI_MODEL
}