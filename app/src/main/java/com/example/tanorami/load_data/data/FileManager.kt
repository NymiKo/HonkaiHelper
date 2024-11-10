package com.example.tanorami.load_data.data

interface FileManager {
    suspend fun saveImage(imageUrl: String, child: String): String
}