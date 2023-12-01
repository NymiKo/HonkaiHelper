package com.example.honkaihelper.create_build_hero

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import com.example.honkaihelper.create_build_hero.adapter.CreateBuildHeroStatsListener
import com.example.honkaihelper.databinding.FragmentCreateBuildHeroBinding
import com.example.honkaihelper.equipment.EquipmentFragment
import com.example.honkaihelper.equipment.EquipmentType
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.utils.backgroundHero
import com.example.honkaihelper.utils.backgroundWeapon
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load
import com.example.honkaihelper.utils.visible
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateBuildHeroFragment :
    BaseFragment<FragmentCreateBuildHeroBinding>(FragmentCreateBuildHeroBinding::inflate) {

    private val viewModel by viewModels<CreateBuildHeroViewModel> { viewModelFactory }
    private var pathHero = 0
    private var equipmentClick = EquipmentType.WEAPON
    private lateinit var mStatsAdapter: CreateBuildHeroStatsAdapter

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)
    private val idBuild get() = requireArguments().getInt(ARG_ID_BUILD)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.createBuildHeroComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (idBuild != -1) viewModel.getBuild(idBuild)
        if (idHero != -1) viewModel.getHero(idHero)
        setFragmentResultListener("equipment_key") { key, bundle ->
            val equipment = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("equipment", Equipment::class.java)
            } else {
                bundle.getParcelable("equipment")
            }

            if (equipment != null) {
                when (equipmentClick) {
                    EquipmentType.WEAPON -> {
                        viewModel.addWeapon(equipment)
                    }

                    EquipmentType.RELIC_TWO_PARTS -> {
                        viewModel.addRelicTwoParts(equipment)
                    }

                    EquipmentType.DECORATION -> {
                        viewModel.addDecoration(equipment)
                    }

                    EquipmentType.RELIC_FOUR_PARTS -> {
                        viewModel.addRelicFourParts(equipment)
                    }
                }
            }
        }
    }

    override fun setupView() {
        getHero()
        setupImageWeapon()
        setupImageRelicTwoParts()
        setupImageRelicFourParts()
        setupImageDecoration()
        setupStatsAdapter()
        setupStatsRecyclerView()
        setupSaveBuildButton()
        setupToolbar()
    }

    override fun uiStateHandle() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is CreateBuildHeroUiState.CREATION -> {
                    binding.progressCreationBuild.gone()
                    binding.buttonSaveBuild.visible()
                }

                is CreateBuildHeroUiState.ERROR -> {
                    binding.progressCreationBuild.gone()
                    binding.buttonSaveBuild.visible()
                    Toast.makeText(requireActivity(), it.errorMessage, Toast.LENGTH_SHORT).show()
                }

                is CreateBuildHeroUiState.SENDING_BUILD -> {
                    binding.progressCreationBuild.visible()
                    binding.buttonSaveBuild.gone()
                }

                is CreateBuildHeroUiState.SUCCESS -> {
                    successResultBuild(R.string.success_create_build)
                }

                is CreateBuildHeroUiState.SUCCESS_DELETION_BUILD -> {
                    successResultBuild(R.string.success_delete_build)
                }
            }
        }
    }

    private fun successResultBuild(message: Int) {
        Toast.makeText(
            requireActivity(),
            message,
            Toast.LENGTH_SHORT
        ).show()
        findNavController().popBackStack()
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

    private fun setupImageRelicTwoParts() {
        viewModel.relicTwoParts.observe(viewLifecycleOwner) {
            binding.imageHeroRelicBuildTwoParts.apply {
                load(it.image)
                imageTintList = null
                background = ContextCompat.getDrawable(requireActivity(), R.color.orange)
            }
        }
        binding.imageHeroRelicBuildTwoParts.setOnClickListener {
            equipmentClick = EquipmentType.RELIC_TWO_PARTS
            findNavController().navigate(
                R.id.equipmentFragment,
                EquipmentFragment.newInstance(equipmentClick = equipmentClick)
            )
        }
    }

    private fun setupImageRelicFourParts() {
        viewModel.relicFourParts.observe(viewLifecycleOwner) {
            binding.imageHeroRelicBuildFourParts.apply {
                load(it?.image)
                imageTintList = null
                background = ContextCompat.getDrawable(requireActivity(), R.color.orange)
            }
        }
        binding.imageHeroRelicBuildFourParts.setOnClickListener {
            equipmentClick = EquipmentType.RELIC_FOUR_PARTS
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
        val list = listOf(
            R.array.stats_in_body,
            R.array.stats_in_legs,
            R.array.stats_in_sphere,
            R.array.stats_in_rope
        )
        mStatsAdapter = CreateBuildHeroStatsAdapter(object : CreateBuildHeroStatsListener {
            override fun onSpinnerItemSelected(adapterPosition: Int, selectedItemPosition: Int) {
                val selectedValue = list[adapterPosition]
                viewModel.changeStatOnEquipment(
                    adapterPosition,
                    resources.getStringArray(selectedValue).toList()[selectedItemPosition]
                )
            }
        })
        mStatsAdapter.list = list
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
            setOnClickListener { viewModel.saveBuild(idBuild) }
        }
    }

    private fun setupDeleteDialog(title: Int, message: Int) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.yes) { _, _ ->
                viewModel.deleteBuild(idBuild)
            }
            .setNegativeButton(R.string.cancellation) { dialog, _ ->
                dialog.cancel()
            }
            .create()
        dialog.show()
    }

    private fun setupToolbar() {
        binding.toolbarCreateBuildHero.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        if (idBuild != -1) {
            binding.toolbarCreateBuildHero.apply {
                title = getString(R.string.edit_build)
                inflateMenu(R.menu.create_build_and_team_menu)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.delete -> {
                            setupDeleteDialog(R.string.delete_build, R.string.delete_the_build)
                        }
                    }
                    true
                }
            }
        }
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"
        private const val ARG_ID_BUILD = "id_build"

        @JvmStatic
        fun newInstance(idHero: Int = -1, idBuild: Int = -1): Bundle {
            return bundleOf(ARG_ID_HERO to idHero, ARG_ID_BUILD to idBuild)
        }
    }
}