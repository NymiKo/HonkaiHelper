package com.example.honkaihelper.di.components

import com.example.honkaihelper.fragments.create_team.CreateTeamFragment
import com.example.honkaihelper.fragments.heroes.HeroesListFragment
import com.example.honkaihelper.fragments.teams.TeamsListFragment
import dagger.Component

@Component
interface MainActivityComponent {

    fun inject(fragment: HeroesListFragment)
    fun inject(fragment: CreateTeamFragment)
    fun inject(fragment: TeamsListFragment)
}