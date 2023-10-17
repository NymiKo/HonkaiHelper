package com.example.honkaihelper.builds_hero

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.builds_hero.adapter.BuildsHeroListAdapter
import com.example.honkaihelper.builds_hero.data.model.BuildHero
import com.example.honkaihelper.databinding.FragmentBuildsHeroListBinding
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.profile.data.model.User
import com.example.honkaihelper.teams.TeamsListFragment

class BuildsHeroListFragment :
    BaseFragment<FragmentBuildsHeroListBinding>(FragmentBuildsHeroListBinding::inflate) {

    private val viewModel by viewModels<BuildsHeroListViewModel> { viewModelFactory }
    private lateinit var mAdapter: BuildsHeroListAdapter

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)
    private val nameHero get() = requireArguments().getString(ARG_NAME_HERO)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.buildsHeroListComponent().create().inject(this)
    }

    override fun setupView() {
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
        navigateToCreateBuild()
    }

    override fun uiStateHandle() {

    }

    private fun setupAdapter() {
        mAdapter = BuildsHeroListAdapter()
        mAdapter.buildsHeroList = listOf(
            BuildHero(
                Hero(1, "Блэйд", "http://f0862137.xsph.ru/images/hero_icon/blade.webp", true, 1),
                Equipment(1, "https://static.wikia.nocookie.net/honkai-star-rail/images/a/a7/Световой_конус_Кроты_приветствуют_тебя_Карточка.png/revision/latest?cb=20230710214741&path-prefix=ru", 2),
                Equipment(1, "https://static.wikia.nocookie.net/honkai-star-rail/images/a/a7/Световой_конус_Кроты_приветствуют_тебя_Карточка.png/revision/latest?cb=20230710214741&path-prefix=ru", 0),
                Equipment(1, "https://static.wikia.nocookie.net/honkai-star-rail/images/a/a7/Световой_конус_Кроты_приветствуют_тебя_Карточка.png/revision/latest?cb=20230710214741&path-prefix=ru", 1),
                User("Nymiko", "http://f0862137.xsph.ru/avatars/10.png", emptyList())
            )
        )
    }

    private fun setupRecyclerView() {
        binding.recyclerBuildsHeroList.adapter = mAdapter
        binding.recyclerBuildsHeroList.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun setupToolbar() {
        binding.toolbarBuildsHeroList.title = resources.getString(R.string.builds_for_hero, nameHero)
    }

    private fun navigateToCreateBuild() {
        binding.buttonCreate.setOnClickListener {
            findNavController().navigate(R.id.createBuildHeroFragment)
        }
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"
        private const val ARG_NAME_HERO = "name_hero"

        @JvmStatic
        fun newInstance(idHero: Int, nameHero: String): Bundle {
            return bundleOf(ARG_ID_HERO to idHero, ARG_NAME_HERO to nameHero)
        }
    }
}