package com.example.tanorami.viewing_users_build

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.base.BaseFragment
import com.example.tanorami.databinding.FragmentViewingUsersBuildBinding
import com.example.tanorami.info_about_decoration.ui.InfoAboutDecorationFragment
import com.example.tanorami.info_about_relic.ui.InfoAboutRelicFragment
import com.example.tanorami.utils.backgroundHero
import com.example.tanorami.utils.backgroundRarity
import com.example.tanorami.utils.gone
import com.example.tanorami.utils.load
import com.example.tanorami.utils.toast
import com.example.tanorami.utils.visible
import com.example.tanorami.viewing_users_build.adapter.ViewingUsersBuildStatsAdapter
import com.example.tanorami.info_about_weapon.ui.InfoAboutWeaponFragment

class ViewingUsersBuildFragment :
    BaseFragment<FragmentViewingUsersBuildBinding>(FragmentViewingUsersBuildBinding::inflate) {

    private val viewModel by viewModels<ViewingUsersBuildViewModel> { viewModelFactory }
    private lateinit var mAdapter: ViewingUsersBuildStatsAdapter

    private val idBuild get() = requireArguments().getLong(ARG_ID_BUILD)
    private val uid get() = requireArguments().getString(ARG_UID_BUILD)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.viewingUsersBuildComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHeroBuild(idBuild, uid)
    }

    override fun setupView() {
        addMenu()
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is ViewingUsersBuildUiState.ERROR -> {
                    toast(requireActivity(), R.string.error)
                    binding.shimmerViewingUsersBuild.gone()
                    binding.shimmerViewingUsersBuild.stopShimmer()
                }

                is ViewingUsersBuildUiState.LOADING -> {
                    binding.shimmerViewingUsersBuild.stopShimmer()
                    binding.shimmerViewingUsersBuild.visible()
                }

                is ViewingUsersBuildUiState.SUCCESS -> {
                    binding.toolbarViewingUsersBuild.title = getString(
                        R.string.hero_build_from,
                        it.fullBuildHeroFromUser.hero.name,
                        it.fullBuildHeroFromUser.nickname
                    )
                    binding.imageHeroAvatarInViewingUsersBuild.backgroundHero(it.fullBuildHeroFromUser.hero.rarity)
                    binding.imageHeroAvatarInViewingUsersBuild.load(it.fullBuildHeroFromUser.hero.localAvatarPath)
                    binding.textHeroNameInViewingUsersBuild.text =
                        it.fullBuildHeroFromUser.hero.name
                    binding.imageViewingHeroWeaponBuild.load(it.fullBuildHeroFromUser.weapon.image)
                    binding.cardViewingHeroWeaponBuild.backgroundRarity(it.fullBuildHeroFromUser.weapon.rarity)
                    binding.imageViewingHeroRelicBuildTwoParts.load(it.fullBuildHeroFromUser.relicTwoParts.image)
                    binding.imageViewingHeroRelicBuildFourParts.load(it.fullBuildHeroFromUser.relicFourParts.image)
                    binding.imageViewingHeroDecorationBuild.load(it.fullBuildHeroFromUser.decoration.image)
                    mAdapter.list = it.fullBuildHeroFromUser.statsEquipment
                    binding.scrollHeroBuild.visible()
                    binding.shimmerViewingUsersBuild.stopShimmer()
                    binding.shimmerViewingUsersBuild.gone()
                    showWeaponInfo(it.fullBuildHeroFromUser.weapon.idWeapon)
                    showRelicTwoPartsInfo(it.fullBuildHeroFromUser.relicTwoParts.idRelic)
                    showRelicFourPartsInfo(it.fullBuildHeroFromUser.relicFourParts.idRelic)
                    showDecorationInfo(it.fullBuildHeroFromUser.decoration.idDecoration)
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

    private fun showWeaponInfo(idWeapon: Int) {
        binding.imageViewingHeroWeaponBuild.setOnClickListener {
            val transitionName = getString(R.string.weapon_info_transition_name)
            val extras = FragmentNavigatorExtras(it to transitionName)
            findNavController().navigate(
                R.id.weaponInfoFragment,
                InfoAboutWeaponFragment.newInstance(idWeapon),
                null,
                extras
            )
        }
    }

    private fun showRelicTwoPartsInfo(idRelic: Int) {
        binding.imageViewingHeroRelicBuildTwoParts.setOnClickListener {
            val transitionName = getString(R.string.relic_info_transition_name)
            val extras = FragmentNavigatorExtras(it to transitionName)
            findNavController().navigate(
                R.id.relicInfoFragment,
                InfoAboutRelicFragment.newInject(idRelic),
                null,
                extras
            )
        }
    }

    private fun showRelicFourPartsInfo(idRelic: Int) {
        binding.imageViewingHeroRelicBuildFourParts.setOnClickListener {
            val transitionName = getString(R.string.relic_info_transition_name)
            val extras = FragmentNavigatorExtras(it to transitionName)
            findNavController().navigate(
                R.id.relicInfoFragment,
                InfoAboutRelicFragment.newInject(idRelic),
                null,
                extras
            )
        }
    }

    private fun showDecorationInfo(idDecoration: Int) {
        binding.imageViewingHeroDecorationBuild.setOnClickListener {
            val transitionName = getString(R.string.decoration_info_transition_name)
            val extras = FragmentNavigatorExtras(it to transitionName)
            findNavController().navigate(
                R.id.decorationInfoFragment,
                InfoAboutDecorationFragment.newInstance(idDecoration),
                null,
                extras
            )
        }
    }

    private fun addMenu() {
        binding.toolbarViewingUsersBuild.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.copy_uid_build -> {
                    viewModel.uiState.observe(viewLifecycleOwner) { buildHero ->
                        val clipboard = requireContext().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                        val clipData: ClipData = ClipData.newPlainText(
                            "UID",
                            if (buildHero is ViewingUsersBuildUiState.SUCCESS) {
                                buildHero.fullBuildHeroFromUser.uid
                            } else {
                                ""
                            }
                        )
                        clipboard.setPrimaryClip(clipData)
                    }
                    Toast.makeText(requireContext(), R.string.message_uid_build_copied, Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    companion object {
        private const val ARG_ID_BUILD = "idBuild"
        private const val ARG_UID_BUILD = "uid"

        fun newInstance(idBuild: Long = -1L, uid: String = ""): Bundle {
            return bundleOf(ARG_ID_BUILD to idBuild, ARG_UID_BUILD to uid)
        }
    }
}