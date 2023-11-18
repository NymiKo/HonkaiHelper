package com.example.honkaihelper.base_build_hero

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.base_build_hero.adapters.DecorationsAdapter
import com.example.honkaihelper.base_build_hero.adapters.ItemClickListener
import com.example.honkaihelper.base_build_hero.adapters.RelicsAdapter
import com.example.honkaihelper.base_build_hero.adapters.StatsEquipmentAdapter
import com.example.honkaihelper.base_build_hero.adapters.WeaponsAdapter
import com.example.honkaihelper.builds_hero_from_users.BuildsHeroListFragment
import com.example.honkaihelper.databinding.FragmentBaseBuildHeroBinding
import com.example.honkaihelper.decoration.DecorationInfoFragment
import com.example.honkaihelper.relic.RelicInfoFragment
import com.example.honkaihelper.weapon.WeaponInfoFragment

class BaseBuildHeroFragment :
    BaseFragment<FragmentBaseBuildHeroBinding>(FragmentBaseBuildHeroBinding::inflate) {

    private val viewModel by viewModels<BaseBuildHeroViewModel> { viewModelFactory }

    private lateinit var mAdapterWeapons: WeaponsAdapter
    private lateinit var mAdapterRelics: RelicsAdapter
    private lateinit var mAdapterDecorations: DecorationsAdapter
    private lateinit var mAdapterStatsEquipment: StatsEquipmentAdapter
    private val idHero get() = requireArguments().getInt(ID_HERO)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.baseBuildHeroComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFullBaseBuildHero(idHero)
    }

    override fun setupView() {
        setupToolbar()
        setupAdapters()
        setupWeaponRecyclerView()
        setupRelicRecyclerView()
        setupDecorationRecyclerView()
        setupStatsEquipmentRecyclerView()
        setupButtonGoToBuildsFromUsers()
    }

    override fun uiStateHandle() {
        viewModel.fullBaseBuildHero.observe(viewLifecycleOwner) {
            mAdapterWeapons.list = it.weapons
            mAdapterRelics.list = it.relics
            mAdapterDecorations.list = it.decoration
            mAdapterStatsEquipment.list = it.buildStatsEquipment
        }
    }

    private fun setupToolbar() {
        binding.toolbarBaseBuildHero.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupAdapters() {
        mAdapterWeapons = WeaponsAdapter(object : ItemClickListener {
            override fun onItemClick(itemId: Int, view: ImageView) {
                val transitionName = getString(R.string.weapon_info_transition_name)
                val extras = FragmentNavigatorExtras(view to transitionName)
                findNavController().navigate(
                    R.id.action_baseBuildHeroFragment_to_weaponInfoFragment,
                    WeaponInfoFragment.newInstance(itemId),
                    null,
                    extras
                )
            }
        })
        mAdapterRelics = RelicsAdapter(object : ItemClickListener {
            override fun onItemClick(itemId: Int, view: ImageView) {
                findNavController().navigate(
                    R.id.relicInfoFragment,
                    RelicInfoFragment.newInject(itemId)
                )
            }
        })
        mAdapterDecorations = DecorationsAdapter(object : ItemClickListener {
            override fun onItemClick(itemId: Int, view: ImageView) {
                findNavController().navigate(
                    R.id.decorationInfoFragment,
                    DecorationInfoFragment.newInject(itemId)
                )
            }
        })
        mAdapterStatsEquipment = StatsEquipmentAdapter()
    }

    private fun setupWeaponRecyclerView() {
        binding.recyclerWeaponBaseBuildHero.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = mAdapterWeapons
            postponeEnterTransition()
            doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
    }

    private fun setupRelicRecyclerView() {
        binding.recyclerRelicBaseBuildHero.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = mAdapterRelics
        }
    }

    private fun setupDecorationRecyclerView() {
        binding.recyclerDecorationBaseBuildHero.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = mAdapterDecorations
        }
    }

    private fun setupStatsEquipmentRecyclerView() {
        binding.recyclerStatsEquipmentBaseBuildHero.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = mAdapterStatsEquipment
        }
    }

    private fun setupButtonGoToBuildsFromUsers() {
        binding.buttonGoToBuildsFromUsers.setOnClickListener {
            findNavController().navigate(R.id.action_baseBuildHeroFragment_to_buildsHeroListFragment, BuildsHeroListFragment.newInstance(idHero))
        }
    }

    override fun onDestroyView() {
        binding.recyclerWeaponBaseBuildHero.adapter = null
        binding.recyclerRelicBaseBuildHero.adapter = null
        binding.recyclerDecorationBaseBuildHero.adapter = null
        binding.recyclerStatsEquipmentBaseBuildHero.adapter = null
        super.onDestroyView()
    }

    companion object {
        private const val ID_HERO = "id"

        @JvmStatic
        fun newInstance(idHero: Int): Bundle {
            return bundleOf(ID_HERO to idHero)
        }
    }
}