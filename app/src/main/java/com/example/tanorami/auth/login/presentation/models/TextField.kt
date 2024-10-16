package com.example.tanorami.auth.login.presentation.models

import com.example.tanorami.R

data class TextField(
    val value: String = "",
    val isError: Boolean = false,
    val errorMessage: Int = R.string.empty_login,
)
