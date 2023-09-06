package com.example.honkaihelper.di.components

import com.example.honkaihelper.fragments.CreateTeamFragment
import com.example.honkaihelper.fragments.HeroesListFragment
import com.example.honkaihelper.fragments.TeamsListFragment
import dagger.Component

@Component
interface MainActivityComponent {

    fun inject(fragment: HeroesListFragment)
    fun inject(fragment: CreateTeamFragment)
    fun inject(fragment: TeamsListFragment)
}