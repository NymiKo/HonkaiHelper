package com.example.tanorami.load_data.presentation.models

import com.example.core.base.UiEffect

sealed interface LoadDataScreenSideEffects : UiEffect {
    data object OnBack : LoadDataScreenSideEffects
}