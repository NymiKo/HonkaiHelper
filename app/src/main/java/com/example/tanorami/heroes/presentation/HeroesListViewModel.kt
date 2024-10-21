package com.example.tanorami.heroes.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class HeroesListViewModel : ViewModel() {
    abstract fun getHeroesList(): Job
    abstract fun getAvatar(): Job
}