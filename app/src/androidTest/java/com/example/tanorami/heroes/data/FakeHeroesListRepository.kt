package com.example.tanorami.heroes.data

import com.example.core.domain.repository.hero.model.HeroModel
import javax.inject.Inject

class FakeHeroesListRepository @Inject constructor() {

    var state: List<HeroModel> = listOf()

}