package com.example.tanorami.create_build_hero.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class EquipmentType: Parcelable {
    WEAPON, RELIC_TWO_PARTS, RELIC_FOUR_PARTS, DECORATION
}
