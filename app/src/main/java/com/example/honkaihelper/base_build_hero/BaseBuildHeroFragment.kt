package com.example.honkaihelper.base_build_hero

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.honkaihelper.App
import com.example.honkaihelper.base_build_hero.adapters.AbilitiesHeroAdapter
import com.example.honkaihelper.databinding.FragmentBaseBuildHeroBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.utils.getParcelable
import com.example.honkaihelper.utils.load
import com.example.honkaihelper.utils.loadImageWithoutScale

class BaseBuildHeroFragment :
    BaseFragment<FragmentBaseBuildHeroBinding>(FragmentBaseBuildHeroBinding::inflate) {

    private val viewModel by viewModels<BaseBuildHeroViewModel> { viewModelFactory }
    private val hero get() = getParcelable(ARG_HERO, Hero::class.java)
    private lateinit var mAdapter: AbilitiesHeroAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.baseBuildHeroComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHeroInfo(hero!!.id)
    }

    override fun setupView() {
        setupToolbar()
        setupHeroSplashArt()
        setupAdapter()
        setupRecyclerView()
    }

    override fun uiStateHandle() {
        viewModel.heroInfo.observe(viewLifecycleOwner) {
            binding.textStoryHero.text = it.hero.story
            binding.imageElementHero.load(it.element.image)
            binding.imagePathHero.load(it.path.image)
            mAdapter.abilitiesList = it.ability
        }
    }

    private fun setupToolbar() {
        binding.toolbarBaseBuildHero.title = hero?.name
    }

    private fun setupHeroSplashArt() = binding.imageHeroAvatarBaseBuild.loadImageWithoutScale(hero?.splashArt)

    private fun setupAdapter() {
        mAdapter = AbilitiesHeroAdapter()
    }

    private fun setupRecyclerView() {
        binding.recyclerAbilitiesHero.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mAdapter
        }
    }

    companion object {
        private const val ARG_HERO = "hero"

        @JvmStatic
        fun newInstance(hero: Hero): Bundle {
            return bundleOf(ARG_HERO to hero)
        }
    }
}