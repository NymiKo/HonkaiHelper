package com.example.honkaihelper.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.R
import com.example.honkaihelper.adapters.HeroTeamsListAdapter
import com.example.honkaihelper.databinding.FragmentTeamsListBinding
import com.example.honkaihelper.models.Hero
import com.example.honkaihelper.models.TeamHero
class TeamsListFragment :
    BaseFragment<FragmentTeamsListBinding>(FragmentTeamsListBinding::inflate) {

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)
    private val nameHero get() = requireArguments().getString(ARG_NAME_HERO)

    private lateinit var mAdapter: HeroTeamsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupToolbar()
        setupRecyclerView()
        openCreateTeamFragment()
    }

    private fun openCreateTeamFragment() {
        binding.buttonCreateTeam.setOnClickListener {
            findNavController().navigate(R.id.action_teamsListFragment_to_createTeamFragment)
        }
    }

    private fun setupRecyclerView() {
        mAdapter = HeroTeamsListAdapter()
        mAdapter.mTeamsHeroList = teamsList
        binding.recyclerViewTeamsHero.adapter = mAdapter
    }

    private fun setupToolbar() {
        binding.toolbarCreateTeam.title = resources.getString(R.string.team_for_hero, nameHero)
    }

    override fun onDestroyView() {
        binding.recyclerViewTeamsHero.adapter = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"
        private const val ARG_NAME_HERO = "name_hero"

        @JvmStatic
        fun newInstance(idHero: Int, nameHero: String): Bundle {
            return bundleOf(ARG_ID_HERO to idHero, ARG_NAME_HERO to nameHero)
        }
    }

    private val teamsList = listOf(
        TeamHero(
            Hero(
                0,
                "Блэйд",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/4/47/Персонаж_Блэйд_Иконка.png/revision/latest?cb=20230721132650&path-prefix=ru",
                true
            ),
            Hero(
                1,
                "Цзинь Юань",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/1/10/Персонаж_Цзин_Юань_Иконка.png/revision/latest?cb=20230219133939&path-prefix=ru",
                true
            ),
            Hero(
                2,
                "Сервал",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/f/f3/Персонаж_Сервал_Иконка.png/revision/latest?cb=20230219133911&path-prefix=ru",
                false
            ),
            Hero(
                3,
                "Тинъюнь",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/d/df/Персонаж_Тинъюнь_Иконка.png/revision/latest?cb=20230510061505&path-prefix=ru",
                false
            )
        ),
        TeamHero(
            Hero(
                4,
                "Гепард",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/6/6b/Персонаж_Гепард_Иконка.png/revision/latest?cb=20230219133835&path-prefix=ru",
                true
            ),
            Hero(
                5,
                "Кафка",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/c/c8/Персонаж_Кафка_Иконка_большая.png/revision/latest?cb=20230712153135&path-prefix=ru",
                true
            ),
            Hero(
                6,
                "Сампо",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/e/e7/Персонаж_Сампо_Иконка.png/revision/latest?cb=20230219133910&path-prefix=ru",
                false
            ),
            Hero(
                7,
                "Лука",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/2/25/Персонаж_Лука_Иконка.png/revision/latest?cb=20230721170410&path-prefix=ru",
                false
            )
        ),
        TeamHero(
            Hero(
                8,
                "ГГ(Огонь)",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/c/cc/Персонаж_Первопроходец_Иконка.png/revision/latest?cb=20230219133909&path-prefix=ru",
                true
            ),
            Hero(
                9,
                "Юйкун",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/6/61/Персонаж_Юйкун_Иконка.png/revision/latest?cb=20230525143141&path-prefix=ru",
                false
            ),
            Hero(
                10,
                "Наташа",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/a/a3/Персонаж_Наташа_Иконка.png/revision/latest?cb=20230219133907&path-prefix=ru",
                false
            ),
            Hero(
                11,
                "Зеле",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/c/c4/Персонаж_Зеле_Иконка.png/revision/latest?cb=20230219133838&path-prefix=ru",
                true
            )
        )
    )
}