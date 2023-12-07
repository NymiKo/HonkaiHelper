package com.example.tanorami.create_build_hero.data.model

data class BuildHeroFromUser(
    val idHero: Int,
    val idWeapon: Int,
    val idRelicTwoParts: Int,
    val idRelicFourParts: Int,
    val idDecoration: Int,
    val statsEquipment: Array<String>,
    val nickname: String = "",
    val idBuild: Int? = null,
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
