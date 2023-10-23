package com.example.honkaihelper.data.image_loader

interface ImageLoader {
    suspend fun downloadAndSaveImage(imageUrl: String, child: String, fileName: String): String
}