package com.example.honkaihelper.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.R
import com.example.honkaihelper.adapters.create_team.CreateTeamAdapter
import com.example.honkaihelper.adapters.create_team.HeroListInCreateTeamAdapter
import com.example.honkaihelper.adapters.create_team.HeroListInCreateTeamListener
import com.example.honkaihelper.databinding.FragmentCreateTeamBinding
import com.example.honkaihelper.models.ActiveHeroInTeam
import com.example.honkaihelper.models.Hero

class CreateTeamFragment :
    BaseFragment<FragmentCreateTeamBinding>(FragmentCreateTeamBinding::inflate) {

    private var hero: Hero? = null
    private lateinit var mAdapterForViewTeam: CreateTeamAdapter
    private lateinit var mAdapterHeroList: HeroListInCreateTeamAdapter
    private val heroesInTeamList = arrayListOf<Hero>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupButtonSaveTeam()
        setupRecyclerViewForViewTeam()
        setupRecyclerViewHeroList()
    }

    private fun setupRecyclerViewForViewTeam() {
        mAdapterForViewTeam = CreateTeamAdapter()
        mAdapterForViewTeam.mHeroInTeamList = heroesInTeamList
        binding.recyclerViewingCommand.adapter = mAdapterForViewTeam
    }

    private fun setupRecyclerViewHeroList() {
        mAdapterHeroList = HeroListInCreateTeamAdapter(object : HeroListInCreateTeamListener {
            override fun onClick(activeHeroInTeam: ActiveHeroInTeam) {
                if (!activeHeroInTeam.active) {
                    mAdapterForViewTeam.addHero(activeHeroInTeam.hero)
                } else {
                    mAdapterForViewTeam.removeHero(activeHeroInTeam.hero)
                }
            }
        })
        mAdapterHeroList.mHeroList = heroList
        binding.recyclerHeroesList.adapter = mAdapterHeroList
        binding.recyclerHeroesList.itemAnimator = null
    }

    private fun setupButtonSaveTeam() {
        binding.buttonSaveTeam.setOnClickListener {
            if (mAdapterForViewTeam.mHeroInTeamList.size == 4) {
                showSaveDialog()
            } else {
                Toast.makeText(requireContext(), "В команде должно быть 4 героя", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showSaveDialog() {
        val dialog = AlertDialog.Builder(requireContext())
            .setMessage(R.string.add_the_created_command)
            .setPositiveButton(R.string.add) { _, _ ->
                Toast.makeText(requireContext(), "Команда добавлена", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
            .setNegativeButton(R.string.cancellation) { dialog, _ ->
                dialog.cancel()
            }
            .create()

        dialog.show()
    }

    override fun onDestroyView() {
        binding.recyclerHeroesList.adapter = null
        binding.recyclerViewingCommand.adapter = null
        super.onDestroyView()
    }

    companion object {

        private const val ARG_PARAM1 = "param1"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateTeamFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    private val heroList = listOf(
        ActiveHeroInTeam(
            Hero(
                0,
                "Блэйд",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/4/47/Персонаж_Блэйд_Иконка.png/revision/latest?cb=20230721132650&path-prefix=ru",
                true
            ), false
        ),
        ActiveHeroInTeam(
            Hero(
                1,
                "Цзинь Юань",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/1/10/Персонаж_Цзин_Юань_Иконка.png/revision/latest?cb=20230219133939&path-prefix=ru",
                true
            ), false
        ),
        ActiveHeroInTeam(
            Hero(
                2,
                "Сервал",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/f/f3/Персонаж_Сервал_Иконка.png/revision/latest?cb=20230219133911&path-prefix=ru",
                false
            ), false
        ),
        ActiveHeroInTeam(
            Hero(
                3,
                "Тинъюнь",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/d/df/Персонаж_Тинъюнь_Иконка.png/revision/latest?cb=20230510061505&path-prefix=ru",
                false
            ), false
        ),
        ActiveHeroInTeam(
            Hero(
                4,
                "Гепард",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/6/6b/Персонаж_Гепард_Иконка.png/revision/latest?cb=20230219133835&path-prefix=ru",
                true
            ), false
        ),
        ActiveHeroInTeam(
            Hero(
                5,
                "Кафка",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/c/c8/Персонаж_Кафка_Иконка_большая.png/revision/latest?cb=20230712153135&path-prefix=ru",
                true
            ), false
        ),
        ActiveHeroInTeam(
            Hero(
                6,
                "Сампо",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/e/e7/Персонаж_Сампо_Иконка.png/revision/latest?cb=20230219133910&path-prefix=ru",
                false
            ), false
        ),
        ActiveHeroInTeam(
            Hero(
                7,
                "Лука",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/2/25/Персонаж_Лука_Иконка.png/revision/latest?cb=20230721170410&path-prefix=ru",
                false
            ), false
        ),
        ActiveHeroInTeam(
            Hero(
                8,
                "ГГ(Огонь)",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/c/cc/Персонаж_Первопроходец_Иконка.png/revision/latest?cb=20230219133909&path-prefix=ru",
                true
            ), false
        ),
        ActiveHeroInTeam(
            Hero(
                9,
                "Юйкун",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/6/61/Персонаж_Юйкун_Иконка.png/revision/latest?cb=20230525143141&path-prefix=ru",
                false
            ), false
        ),
        ActiveHeroInTeam(
            Hero(
                10,
                "Наташа",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/a/a3/Персонаж_Наташа_Иконка.png/revision/latest?cb=20230219133907&path-prefix=ru",
                false
            ), false
        ),
        ActiveHeroInTeam(
            Hero(
                11,
                "Зеле",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/c/c4/Персонаж_Зеле_Иконка.png/revision/latest?cb=20230219133838&path-prefix=ru",
                true
            ), false
        )
    )
}