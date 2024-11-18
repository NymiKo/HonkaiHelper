package com.example.domain.repository.hero.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroModel(
    val id: Int,
    val name: String,
    val story: String,
    val avatar: String,
    val splashArt: String,
    val rarity: Boolean,
    val idPath: Int,
    val idElement: Int
): Parcelable
