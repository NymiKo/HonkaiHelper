package com.example.honkaihelper.viewing_users_build

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.databinding.FragmentViewingUsersBuildBinding
import com.example.honkaihelper.utils.backgroundHero
import com.example.honkaihelper.utils.backgroundWeapon
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load
import com.example.honkaihelper.utils.visible
import com.example.honkaihelper.viewing_users_build.adapter.ViewingUsersBuildStatsAdapter

class ViewingUsersBuildFragment : BaseFragment<FragmentViewingUsersBuildBinding>(FragmentViewingUsersBuildBinding::inflate) {

    private val viewModel by viewModels<ViewingUsersBuildViewModel> { viewModelFactory }
    private lateinit var mAdapter: ViewingUsersBuildStatsAdapter

    private val idBuild get() = requireArguments().getInt(ARG_ID_BUILD)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.viewingUsersBuildComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHeroBuild(idBuild)
    }

    override fun setupView() {
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
                is ViewingUsersBuildUiState.ERROR -> {

                }
                is ViewingUsersBuildUiState.LOADING -> {
                    binding.progressViewingUsersBuild.visible()
                    binding.scrollHeroBuild.gone()
                }
                is ViewingUsersBuildUiState.SUCCESS -> {
                    binding.toolbarViewingUsersBuild.title = getString(R.string.hero_build_from, it.fullBuildHeroFromUser.hero.name, it.fullBuildHeroFromUser.nickname)
                    binding.imageHeroAvatarInViewingUsersBuild.backgroundHero(it.fullBuildHeroFromUser.hero.rarity)
                    binding.imageHeroAvatarInViewingUsersBuild.load(it.fullBuildHeroFromUser.hero.localAvatarPath)
                    binding.textHeroNameInViewingUsersBuild.text = it.fullBuildHeroFromUser.hero.name
                    binding.imageViewingHeroWeaponBuild.load(it.fullBuildHeroFromUser.weapon.image)
                    binding.imageViewingHeroWeaponBuild.backgroundWeapon(it.fullBuildHeroFromUser.weapon.rarity)
                    binding.imageViewingHeroRelicBuildTwoParts.load(it.fullBuildHeroFromUser.relicTwoParts.image)
                    binding.imageViewingHeroRelicBuildFourParts.load(it.fullBuildHeroFromUser.relicFourParts.image)
                    binding.imageViewingHeroDecorationBuild.load(it.fullBuildHeroFromUser.decoration.image)
                    mAdapter.list = it.fullBuildHeroFromUser.statsEquipment
                    binding.scrollHeroBuild.visible()
                    binding.progressViewingUsersBuild.gone()
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbarViewingUsersBuild.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupAdapter() {
        mAdapter = ViewingUsersBuildStatsAdapter()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewingStatsEquipmentBuild.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mAdapter
        }
    }

    companion object {
        private const val ARG_ID_BUILD = "idBuild"

        fun newInstance(idBuild: Int): Bundle {
            return bundleOf(ARG_ID_BUILD to idBuild)
        }
    }
}