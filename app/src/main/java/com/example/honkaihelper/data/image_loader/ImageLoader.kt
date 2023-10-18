package com.example.honkaihelper.data.image_loader

interface ImageLoader {
    fun downloadAndSaveImage(imageUrl: String, child: String): String
}