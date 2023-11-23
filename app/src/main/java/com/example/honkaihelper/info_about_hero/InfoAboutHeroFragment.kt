package com.example.honkaihelper.info_about_hero

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.base_build_hero.BaseBuildHeroFragment
import com.example.honkaihelper.databinding.FragmentInfoAboutHeroBinding
import com.example.honkaihelper.info_about_hero.adapters.AbilitiesHeroAdapter
import com.example.honkaihelper.info_about_hero.adapters.EidolonsHeroAdapter
import com.example.honkaihelper.teams.TeamsListFragment
import com.example.honkaihelper.utils.loadImageRarity
import com.example.honkaihelper.utils.loadImageWithoutScale
import com.example.honkaihelper.utils.themeColor
import com.google.android.material.transition.MaterialContainerTransform

class InfoAboutHeroFragment :
    BaseFragment<FragmentInfoAboutHeroBinding>(FragmentInfoAboutHeroBinding::inflate) {

    private val viewModel by viewModels<InfoAboutHeroViewModel> { viewModelFactory }
    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)
    private lateinit var mAbilitiesAdapter: AbilitiesHeroAdapter
    private lateinit var mEidolonsAdapter: EidolonsHeroAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.infoAboutHeroComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHeroInfo(idHero)
    }

    override fun setupView() {
        setupToolbar()
        setupAbilitiesAdapter()
        setupEidolonsAdapter()
        setupAbilitiesRecyclerView()
        setupEidolonsRecyclerView()
        setupGoToBuildHeroButton()
        setupButtonGoToTeamsFromUsers()
    }

    override fun uiStateHandle() {
        viewModel.heroInfo.observe(viewLifecycleOwner) {
            binding.toolbarInfoAboutHero.title = it.hero.name
            binding.imageAvatarInfoAboutHero.loadImageWithoutScale(it.hero.splashArt)
            binding.textStoryHero.text = it.hero.story
            binding.imageElementHero.loadImageWithoutScale(it.element.image)
            binding.imagePathHero.loadImageWithoutScale(it.path.image)
            binding.imageRarityHero.loadImageRarity(it.hero.rarity)
            mAbilitiesAdapter.abilitiesList = it.ability
            mEidolonsAdapter.eidolonList = it.eidolon
        }
    }

    private fun setupToolbar() {
        binding.toolbarInfoAboutHero.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setupAbilitiesAdapter() {
        mAbilitiesAdapter = AbilitiesHeroAdapter()
    }

    private fun setupEidolonsAdapter() {
        mEidolonsAdapter = EidolonsHeroAdapter()
    }

    private fun setupAbilitiesRecyclerView() {
        binding.recyclerAbilitiesHero.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mAbilitiesAdapter
        }
    }

    private fun setupEidolonsRecyclerView() {
        binding.recyclerEidolonsHero.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mEidolonsAdapter
        }
    }

    private fun setupGoToBuildHeroButton() {
        binding.buttonGoToBuildHero.setOnClickListener {
            findNavController().navigate(R.id.action_infoAboutHeroFragment_to_baseBuildHeroFragment, BaseBuildHeroFragment.newInstance(idHero))
        }
    }

    private fun setupButtonGoToTeamsFromUsers() {
        binding.buttonGoToTeamsFromUsers.setOnClickListener {
            findNavController().navigate(R.id.action_infoAboutHeroFragment_to_teamsListFragment, TeamsListFragment.newInstance(idHero))
        }
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"

        @JvmStatic
        fun newInstance(idHero: Int): Bundle {
            return bundleOf(ARG_ID_HERO to idHero)
        }
    }
}