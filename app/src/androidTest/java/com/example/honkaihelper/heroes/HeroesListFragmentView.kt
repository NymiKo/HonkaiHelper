package com.example.honkaihelper.heroes

import com.example.honkaihelper.R
import com.example.honkaihelper.base.FragmentsView

class HeroesListFragmentView : FragmentsView() {

    val recyclerViewHeroesList = R.id.recyclerViewHeroes.view()
    val profileIcon = R.id.button_profile.view()
    val searchItemMenu = R.id.search.view()
    val searchItemText = com.google.android.material.R.id.search_src_text.view()

}