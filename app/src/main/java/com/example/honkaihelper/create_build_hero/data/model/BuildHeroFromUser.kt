package com.example.honkaihelper.create_build_hero.data.model

data class BuildHeroFromUser(
    val idHero: Int,
    val idWeapon: Int,
    val idRelic: Int,
    val idDecoration: Int,
    val statsEquipment: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BuildHeroFromUser

        if (!statsEquipment.contentEquals(other.statsEquipment)) return false

        return true
    }

    override fun hashCode(): Int {
        return statsEquipment.contentHashCode()
    }
}
