package com.example.honkaihelper.equipment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class EquipmentType: Parcelable {
    WEAPON, RELIC, DECORATION
}
