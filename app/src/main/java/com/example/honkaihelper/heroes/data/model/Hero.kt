package com.example.honkaihelper.heroes.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    val id: Int,
    val name: String,
    val story: String,
    val avatar: String,
    val splashArt: String,
    val rarity: Boolean,
    val path: Int,
    val element: Int
): Parcelable
