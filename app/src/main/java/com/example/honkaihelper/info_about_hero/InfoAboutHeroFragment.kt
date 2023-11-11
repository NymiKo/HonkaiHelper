package com.example.honkaihelper.info_about_hero

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.info_about_hero.adapters.AbilitiesHeroAdapter
import com.example.honkaihelper.info_about_hero.adapters.EidolonsHeroAdapter
import com.example.honkaihelper.databinding.FragmentInfoAboutHeroBinding
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.base_build_hero.BaseBuildHeroFragment
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.utils.getParcelable
import com.example.honkaihelper.utils.load
import com.example.honkaihelper.utils.loadImageRarity
import com.example.honkaihelper.utils.loadImageWithoutScale

class InfoAboutHeroFragment :
    BaseFragment<FragmentInfoAboutHeroBinding>(FragmentInfoAboutHeroBinding::inflate) {

    private val viewModel by viewModels<InfoAboutHeroViewModel> { viewModelFactory }
    private val hero get() = getParcelable(ARG_HERO, Hero::class.java)
    private lateinit var mAbilitiesAdapter: AbilitiesHeroAdapter
    private lateinit var mEidolonsAdapter: EidolonsHeroAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.infoAboutHeroComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHeroInfo(hero!!.id)
    }

    override fun setupView() {
        setupToolbar()
        setupAbilitiesAdapter()
        setupEidolonsAdapter()
        setupAbilitiesRecyclerView()
        setupEidolonsRecyclerView()
        setupGoToBuildHeroButton()
    }

    override fun uiStateHandle() {
        viewModel.heroInfo.observe(viewLifecycleOwner) {
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
            title = hero?.name
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
            findNavController().navigate(R.id.action_infoAboutHeroFragment_to_baseBuildHeroFragment, BaseBuildHeroFragment.newInstance(hero!!.id))
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