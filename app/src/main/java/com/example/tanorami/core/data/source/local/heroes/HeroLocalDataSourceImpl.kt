package com.example.tanorami.core.data.source.local.heroes

import com.example.tanorami.core.data.source.local.heroes.mapper.toHeroEntity
import com.example.tanorami.core.database.dao.HeroDao
import com.example.tanorami.core.database.models.hero.FullBaseBuildHeroEntity
import com.example.tanorami.core.database.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.core.database.models.hero.HeroWithPathAndElement
import com.example.tanorami.heroes.data.model.Hero
import javax.inject.Inject

class HeroLocalDataSourceImpl @Inject constructor(
    private val heroDao: HeroDao,
) : HeroLocalDataSource {
    override suspend fun getHeroesList(): List<Hero> {
        return heroDao.getHeroesList().map { heroEntity -> heroEntity.toHero() }
    }

    override suspend fun getHeroWithPathAndElement(idHero: Int): HeroWithPathAndElement {
        return heroDao.getHeroWithPathAndElement(idHero)
    }

    override suspend fun getFullBaseBuildHero(idHero: Int): FullBaseBuildHeroEntity {
        return heroDao.getFullBaseBuildHero(idHero)
    }

    override suspend fun getHeroWithNameAvatarRarity(idHero: Int): HeroWithNameAvatarRarity {
        return heroDao.getHeroWithNameAvatarRarity(idHero)
    }

    override suspend fun getHeroesListWithNameAvatarRarity(): List<HeroWithNameAvatarRarity> {
        return heroDao.getHeroesListWithNameAvatarRarity()
    }

    override suspend fun getHeroById(idHero: Int): Hero {
        return heroDao.getHeroById(idHero).toHero()
    }

    override suspend fun getHeroNameById(idHero: Int): String {
        return heroDao.getHeroNameById(idHero)
    }

    override suspend fun insertHeroesList(heroesList: List<Hero>) {
        heroDao.insertHeroesList(heroesList.map { hero -> hero.toHeroEntity() })
    }
}