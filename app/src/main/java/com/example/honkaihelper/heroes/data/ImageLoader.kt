package com.example.honkaihelper.heroes.data

interface ImageLoader {
    fun downloadAndSaveImage(imageUrl: String): String
}