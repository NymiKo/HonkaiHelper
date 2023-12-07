package com.example.tanorami.base

import com.example.tanorami.createteam.CreateTeamFragmentView
import com.example.tanorami.heroes.HeroesListFragmentView
import com.example.tanorami.login.LoginFragmentView
import com.example.tanorami.profile.ProfileFragmentView
import com.example.tanorami.registration.RegistrationFragmentView
import com.example.tanorami.teams.TeamsListFragmentView

abstract class BaseView: BaseTest() {

    protected val heroesListFragmentView = HeroesListFragmentView()
    protected val profileFragmentView = ProfileFragmentView()
    protected val loginFragmentView = LoginFragmentView()
    protected val registrationFragmentView = RegistrationFragmentView()
    protected val teamsListFragmentView = TeamsListFragmentView()
    protected val createTeamFragmentView = CreateTeamFragmentView()

}