package com.example.tanorami.heroes

import com.example.tanorami.R
import com.example.tanorami.base.FragmentsView

class HeroesListFragmentView : FragmentsView() {

    val recyclerViewHeroesList = R.id.recyclerViewHeroes.view()
    val profileIcon = R.id.button_profile.view()
    val searchItemMenu = R.id.search.view()
    val searchItemText = com.google.android.material.R.id.search_src_text.view()
    val groupRetry = R.id.group_retry.view()

}