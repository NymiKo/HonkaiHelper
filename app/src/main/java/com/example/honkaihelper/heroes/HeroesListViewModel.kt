package com.example.honkaihelper.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.honkaihelper.models.Hero
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(): ViewModel() {

    private val heroes = listOf(
        Hero(0, "Блэйд", "https://static.wikia.nocookie.net/honkai-star-rail/images/4/47/Персонаж_Блэйд_Иконка.png/revision/latest?cb=20230721132650&path-prefix=ru", true),
        Hero(1, "Цзинь Юань", "https://i.ibb.co/fMwpQCr/Upscales-ai-1693505854653.jpg", true),
        Hero(2, "Сервал", "https://static.wikia.nocookie.net/honkai-star-rail/images/f/f3/Персонаж_Сервал_Иконка.png/revision/latest?cb=20230219133911&path-prefix=ru", false)
    )

    private val _heroesList = MutableLiveData<List<Hero>>().apply { value = heroes }
    val heroesList: LiveData<List<Hero>> = _heroesList


}