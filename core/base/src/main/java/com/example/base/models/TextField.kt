package com.example.base.models

data class TextField(
    val value: String = "",
    val isError: Boolean = false,
    val errorMessage: Int = 0,
    val visualTransformation: Boolean = false,
)
