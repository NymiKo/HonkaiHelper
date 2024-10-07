package com.example.tanorami.info_about_hero.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tanorami.App
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.info_about_hero.adapters.AbilitiesHeroAdapter
import com.example.tanorami.info_about_hero.adapters.EidolonsHeroAdapter
import com.example.tanorami.info_about_hero.presentation.InfoAboutHeroViewModel
import javax.inject.Inject

class InfoAboutHeroFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<InfoAboutHeroViewModel> { viewModelFactory }
    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)
    private lateinit var mAbilitiesAdapter: AbilitiesHeroAdapter
    private lateinit var mEidolonsAdapter: EidolonsHeroAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.infoAboutHeroComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    InfoAboutHeroScreen(
                        idHero = idHero,
                        viewModel = viewModel,
                        navController = findNavController()
                    )
                }
            }
        }
    }

    fun setupView() {
        setupToolbar()
        setupAbilitiesAdapter()
        setupEidolonsAdapter()
        setupAbilitiesRecyclerView()
        setupEidolonsRecyclerView()
        setupGoToBuildHeroButton()
        setupButtonGoToTeamsFromUsers()
    }

    fun uiStateHandle() {
//        viewModel.heroInfo.observe(viewLifecycleOwner) {
//            binding.toolbarInfoAboutHero.title = it.hero.name
//            binding.imageAvatarInfoAboutHero.loadImageWithoutScale(it.hero.splashArt)
//            binding.textStoryHero.text = it.hero.story
//            binding.imageElementHero.loadImageWithoutScale(it.element.image)
//            binding.imagePathHero.loadImageWithoutScale(it.path.image)
//            binding.imageRarityHero.loadImageRarity(it.hero.rarity)
//            mAbilitiesAdapter.abilitiesList = it.ability
//            mEidolonsAdapter.eidolonList = it.eidolon
//        }
    }

    private fun setupToolbar() {
//        binding.toolbarInfoAboutHero.apply {
//            setNavigationOnClickListener {
//                findNavController().popBackStack()
//            }
//        }
    }

    private fun setupAbilitiesAdapter() {
        mAbilitiesAdapter = AbilitiesHeroAdapter()
    }

    private fun setupEidolonsAdapter() {
        mEidolonsAdapter = EidolonsHeroAdapter()
    }

    private fun setupAbilitiesRecyclerView() {
//        binding.recyclerAbilitiesHero.apply {
//            layoutManager = LinearLayoutManager(requireActivity())
//            adapter = mAbilitiesAdapter
//        }
    }

    private fun setupEidolonsRecyclerView() {
//        binding.recyclerEidolonsHero.apply {
//            layoutManager = LinearLayoutManager(requireActivity())
//            adapter = mEidolonsAdapter
//        }
    }

    private fun setupGoToBuildHeroButton() {
//        binding.buttonGoToBuildHero.setOnClickListener {
//            findNavController().navigate(R.id.action_infoAboutHeroFragment_to_baseBuildHeroFragment, BaseBuildHeroFragment.newInstance(idHero))
//        }
    }

    private fun setupButtonGoToTeamsFromUsers() {
//        binding.buttonGoToTeamsFromUsers.setOnClickListener {
//            findNavController().navigate(R.id.action_infoAboutHeroFragment_to_teamsListFragment, TeamsListFragment.newInstance(idHero))
//        }
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"

        @JvmStatic
        fun newInstance(idHero: Int): Bundle {
            return bundleOf(ARG_ID_HERO to idHero)
        }
    }
}