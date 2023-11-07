package com.example.honkaihelper.base_build_hero

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.App
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.base_build_hero.adapters.DecorationsAdapter
import com.example.honkaihelper.base_build_hero.adapters.RelicsAdapter
import com.example.honkaihelper.base_build_hero.adapters.WeaponsAdapter
import com.example.honkaihelper.base_build_hero.data.model.Weapon
import com.example.honkaihelper.databinding.FragmentBaseBuildHeroBinding
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.info_about_hero.data.model.Decoration
import com.example.honkaihelper.info_about_hero.data.model.Relic

class BaseBuildHeroFragment :
    BaseFragment<FragmentBaseBuildHeroBinding>(FragmentBaseBuildHeroBinding::inflate) {

    private val viewModel by viewModels<BaseBuildHeroViewModel> { viewModelFactory }

    private lateinit var mAdapterWeapons: WeaponsAdapter
    private lateinit var mAdapterRelics: RelicsAdapter
    private lateinit var mAdapterDecorations: DecorationsAdapter
    private val idHero get() = requireArguments().getInt(ID_HERO)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.baseBuildHeroComponent().create().inject(this)
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
    }

    override fun uiStateHandle() {
        viewModel.fullBaseBuildHero.observe(viewLifecycleOwner) {
            mAdapterWeapons.list = it.weapons
            mAdapterRelics.list = it.relics
            mAdapterDecorations.list = it.decoration
        }
    }

    private fun setupToolbar() {
        binding.toolbarBaseBuildHero.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupAdapters() {
        mAdapterWeapons = WeaponsAdapter()
        mAdapterRelics = RelicsAdapter()
        mAdapterDecorations = DecorationsAdapter()
    }

    private fun setupWeaponRecyclerView() {
        binding.recyclerWeaponBaseBuildHero.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = mAdapterWeapons
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

    override fun onDestroyView() {
        binding.recyclerWeaponBaseBuildHero.adapter = null
        binding.recyclerRelicBaseBuildHero.adapter = null
        binding.recyclerDecorationBaseBuildHero.adapter = null
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