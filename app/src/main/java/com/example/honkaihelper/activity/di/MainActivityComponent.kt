package com.example.honkaihelper.activity.di

import com.example.honkaihelper.createteam.CreateTeamFragment
import com.example.honkaihelper.heroes.HeroesListFragment
import com.example.honkaihelper.teams.TeamsListFragment
import dagger.Component

@Component
interface MainActivityComponent {

    fun inject(fragment: HeroesListFragment)
    fun inject(fragment: CreateTeamFragment)
    fun inject(fragment: TeamsListFragment)
}