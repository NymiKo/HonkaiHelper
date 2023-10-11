package com.example.honkaihelper.heroes.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    val id: Int,
    val name: String,
    val avatar: String,
    val rarity: Boolean,
    val path: Int
): Parcelable
