package com.example.honkaihelper.heroes.data

import com.example.honkaihelper.models.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroesListRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher
): HeroesListRepository {

    override suspend fun getHeroesList(): List<Hero> {
        return withContext(ioDispatcher) {
            val heroes = arrayListOf<Hero>()
            heroes.add(Hero(0, "Блэйд", "https://static.wikia.nocookie.net/honkai-star-rail/images/4/47/Персонаж_Блэйд_Иконка.png/revision/latest?cb=20230721132650&path-prefix=ru", true))
            heroes.add(Hero(1, "Цзинь Юань", "https://i.ibb.co/fMwpQCr/Upscales-ai-1693505854653.jpg", true))
            heroes.add(Hero(2, "Сервал", "https://static.wikia.nocookie.net/honkai-star-rail/images/f/f3/Персонаж_Сервал_Иконка.png/revision/latest?cb=20230219133911&path-prefix=ru", false))
            return@withContext heroes
        }
    }
}