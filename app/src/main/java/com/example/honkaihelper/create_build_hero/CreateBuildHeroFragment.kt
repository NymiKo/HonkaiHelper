package com.example.honkaihelper.create_build_hero

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.create_build_hero.adapter.CreateBuildHeroStatsAdapter
import com.example.honkaihelper.databinding.FragmentCreateBuildHeroBinding
import com.example.honkaihelper.equipment.EquipmentFragment
import com.example.honkaihelper.equipment.EquipmentType
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.utils.backgroundHero
import com.example.honkaihelper.utils.backgroundWeapon
import com.example.honkaihelper.utils.getParcelable
import com.example.honkaihelper.utils.load
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateBuildHeroFragment :
    BaseFragment<FragmentCreateBuildHeroBinding>(FragmentCreateBuildHeroBinding::inflate) {

    private val viewModel by viewModels<CreateBuildHeroViewModel> { viewModelFactory }
    private var pathHero = 0
    private var equipmentClick = EquipmentType.WEAPON
    private lateinit var mStatsAdapter: CreateBuildHeroStatsAdapter

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.createBuildHeroComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHero(idHero)
        setFragmentResultListener("equipment_key") { key, bundle ->
            val equipment = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("equipment", Equipment::class.java)
            } else {
                bundle.getParcelable("equipment")
            }

            if (equipment != null) {
                when(equipmentClick) {
                    EquipmentType.WEAPON -> {
                        viewModel.addWeapon(equipment)
                    }

                    EquipmentType.RELIC -> {
                        viewModel.addRelic(equipment)
                    }

                    EquipmentType.DECORATION -> {
                        viewModel.addDecoration(equipment)
                    }
                }
            }
        }
    }

    override fun setupView() {
        getHero()
        setupImageWeapon()
        setupImageRelic()
        setupImageDecoration()
        setupStatsAdapter()
        setupStatsRecyclerView()
        setupSaveBuildButton()
    }

    override fun uiStateHandle() {

    }

    private fun getHero() {
        viewModel.hero.observe(viewLifecycleOwner) {
            binding.imageHeroAvatarInCreateBuildr.load(it.avatar)
            binding.imageHeroAvatarInCreateBuildr.backgroundHero(it.rarity)
            binding.textHeroNameInCreateBuild.text = it.name
            pathHero = it.path
        }
    }

    private fun setupImageWeapon() {
        viewModel.weapon.observe(viewLifecycleOwner) {
            binding.imageHeroWeaponBuild.apply {
                load(it.image)
                imageTintList = null
                backgroundWeapon(it.rarity.toInt())
            }
        }
        binding.imageHeroWeaponBuild.setOnClickListener {
            equipmentClick = EquipmentType.WEAPON
            findNavController().navigate(
                R.id.equipmentFragment,
                EquipmentFragment.newInstance(pathHero, equipmentClick = equipmentClick)
            )
        }
    }

    private fun setupImageRelic() {
        viewModel.relic.observe(viewLifecycleOwner) {
            binding.imageHeroRelicBuild.apply {
                load(it.image)
                imageTintList = null
                background = ContextCompat.getDrawable(requireActivity(), R.color.orange)
            }
        }
        binding.imageHeroRelicBuild.setOnClickListener {
            equipmentClick = EquipmentType.RELIC
            findNavController().navigate(
                R.id.equipmentFragment,
                EquipmentFragment.newInstance(equipmentClick = equipmentClick)
            )
        }
    }

    private fun setupImageDecoration() {
        viewModel.decoration.observe(viewLifecycleOwner) {
            binding.imageHeroDecorationBuild.apply {
                load(it.image)
                imageTintList = null
                background = ContextCompat.getDrawable(requireActivity(), R.color.orange)
            }
        }
        binding.imageHeroDecorationBuild.setOnClickListener {
            equipmentClick = EquipmentType.DECORATION
            findNavController().navigate(
                R.id.equipmentFragment,
                EquipmentFragment.newInstance(equipmentClick = equipmentClick)
            )
        }
    }

    private fun setupStatsAdapter() {
        mStatsAdapter = CreateBuildHeroStatsAdapter()
        mStatsAdapter.list = listOf(
            R.array.stats_in_body,
            R.array.stats_in_legs,
            R.array.stats_in_sphere,
            R.array.stats_in_rope
        )
    }

    private fun setupStatsRecyclerView() {
        binding.recyclerStatsEquipmentCreateBuild.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mStatsAdapter
        }
    }

    private fun setupSaveBuildButton() {
        binding.buttonSaveBuild.apply {
            size = FloatingActionButton.SIZE_MINI
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