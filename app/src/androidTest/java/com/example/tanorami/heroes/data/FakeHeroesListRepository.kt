package com.example.tanorami.heroes.data

import javax.inject.Inject

class FakeHeroesListRepository @Inject constructor() {

    var state: List<com.example.domain.repository.hero.model.HeroModel> = listOf()

}