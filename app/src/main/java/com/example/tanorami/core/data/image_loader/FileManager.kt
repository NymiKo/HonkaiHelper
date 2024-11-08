package com.example.tanorami.core.data.image_loader

interface FileManager {
    suspend fun saveImage(imageUrl: String, child: String): String
}