package com.example.tanorami.load_data.presentation.models

sealed interface LoadDataScreenSideEffects : com.example.base.UiEffect {
    data object OnBack : LoadDataScreenSideEffects
}