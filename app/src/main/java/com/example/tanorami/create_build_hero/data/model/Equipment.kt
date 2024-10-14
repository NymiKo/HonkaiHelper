package com.example.tanorami.create_build_hero.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Equipment(val id: Int, val image: String = "", val rarity: Int = 2) : Parcelable
