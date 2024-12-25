package com.example.tanorami.main.data.models

data class NewDataResponse(
    val remoteVersionDB: String,
    val message: String,
    val headerImportantMessage: String,
    val importantMessage: String,
    val show: Boolean,
    val versionImportantMessage: Int,
)
