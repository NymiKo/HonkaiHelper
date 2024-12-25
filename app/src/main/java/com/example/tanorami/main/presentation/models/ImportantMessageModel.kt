package com.example.tanorami.main.presentation.models

data class ImportantMessageModel(
    val headerImportantMessage: String = "",
    val importantMessage: String = "",
    val show: Boolean = false,
    val versionImportantMessage: Int = 0,
)
