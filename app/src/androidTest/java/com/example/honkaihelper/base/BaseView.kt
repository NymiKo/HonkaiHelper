package com.example.honkaihelper.base

import com.example.honkaihelper.createteam.CreateTeamFragmentView
import com.example.honkaihelper.heroes.HeroesListFragmentView
import com.example.honkaihelper.login.LoginFragmentView
import com.example.honkaihelper.profile.ProfileFragmentView
import com.example.honkaihelper.registration.RegistrationFragmentView
import com.example.honkaihelper.teams.TeamsListFragmentView

abstract class BaseView: BaseTest() {

    protected val heroesListFragmentView = HeroesListFragmentView()
    protected val profileFragmentView = ProfileFragmentView()
    protected val loginFragmentView = LoginFragmentView()
    protected val registrationFragmentView = RegistrationFragmentView()
    protected val teamsListFragmentView = TeamsListFragmentView()
    protected val createTeamFragmentView = CreateTeamFragmentView()

}