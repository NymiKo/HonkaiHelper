package com.example.honkaihelper.equipment.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Equipment(val id: Int, val image: String, val rarity: Byte): Parcelable
