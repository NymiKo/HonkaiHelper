package com.example.tanorami.create_build_hero.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.create_build_hero.adapter.CreateBuildHeroStatsAdapter
import com.example.tanorami.create_build_hero.adapter.CreateBuildHeroStatsListener
import com.example.tanorami.create_build_hero.data.model.BuildHeroModel
import com.example.tanorami.databinding.FragmentCreateBuildHeroBinding
import com.example.tanorami.equipment.EquipmentType
import com.example.tanorami.equipment.data.model.Equipment
import javax.inject.Inject

class CreateBuildHeroFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var _binding: FragmentCreateBuildHeroBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CreateBuildHeroViewModel> { viewModelFactory }
    private var pathHero = 0
    private var equipmentClick = EquipmentType.WEAPON
    private lateinit var mStatsAdapter: CreateBuildHeroStatsAdapter

    private val idBuild get() = requireArguments().getLong(ARG_ID_BUILD)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.createBuildHeroComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBuildHeroBinding.inflate(inflater).apply {
            composeView.setContent {
                AppTheme {
                    CreateBuildHeroScreen(
                        viewModel = viewModel,
                        idBuild = idBuild,
                        onBack = { findNavController().navigateUp() }
                    )
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiStateHandle()
        setupView()
    }

    fun setupView() {
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

    fun uiStateHandle() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is CreateBuildHeroUiState.CREATION -> {
//                    binding.progressCreationBuild.gone()
//                    binding.buttonSaveBuild.visible()
                }

                is CreateBuildHeroUiState.ERROR -> {
//                    binding.progressCreationBuild.gone()
//                    binding.buttonSaveBuild.visible()
                    Toast.makeText(requireActivity(), it.errorMessage, Toast.LENGTH_SHORT).show()
                }

                is CreateBuildHeroUiState.SENDING_BUILD -> {
//                    binding.progressCreationBuild.visible()
//                    binding.buttonSaveBuild.gone()
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
//            binding.imageHeroAvatarInCreateBuildr.load(it.avatar)
//            binding.imageHeroAvatarInCreateBuildr.backgroundHero(it.rarity)
//            binding.textHeroNameInCreateBuild.text = it.name
//            pathHero = it.path
        }
    }

    private fun setupImageWeapon() {
        viewModel.weapon.observe(viewLifecycleOwner) {
//            binding.imageHeroWeaponBuild.apply {
//                load(it.image)
//                imageTintList = null
//                backgroundWeapon(it.rarity.toInt())
//            }
        }
//        binding.imageHeroWeaponBuild.setOnClickListener {
//            equipmentClick = EquipmentType.WEAPON
//            findNavController().navigate(
//                R.id.equipmentFragment,
//                EquipmentFragment.newInstance(pathHero, equipmentClick = equipmentClick)
//            )
//        }
    }

    private fun setupImageRelicTwoParts() {
//        viewModel.relicTwoParts.observe(viewLifecycleOwner) {
//            binding.imageHeroRelicBuildTwoParts.apply {
//                load(it.image)
//                imageTintList = null
//                background = ContextCompat.getDrawable(requireActivity(), R.color.orange)
//            }
//        }
//        binding.imageHeroRelicBuildTwoParts.setOnClickListener {
//            equipmentClick = EquipmentType.RELIC_TWO_PARTS
//            findNavController().navigate(
//                R.id.equipmentFragment,
//                EquipmentFragment.newInstance(equipmentClick = equipmentClick)
//            )
//        }
    }

    private fun setupImageRelicFourParts() {
//        viewModel.relicFourParts.observe(viewLifecycleOwner) {
//            binding.imageHeroRelicBuildFourParts.apply {
//                load(it?.image)
//                imageTintList = null
//                background = ContextCompat.getDrawable(requireActivity(), R.color.orange)
//            }
//        }
//        binding.imageHeroRelicBuildFourParts.setOnClickListener {
//            equipmentClick = EquipmentType.RELIC_FOUR_PARTS
//            findNavController().navigate(
//                R.id.equipmentFragment,
//                EquipmentFragment.newInstance(equipmentClick = equipmentClick)
//            )
//        }
    }

    private fun setupImageDecoration() {
//        viewModel.decoration.observe(viewLifecycleOwner) {
//            binding.imageHeroDecorationBuild.apply {
//                load(it.image)
//                imageTintList = null
//                background = ContextCompat.getDrawable(requireActivity(), R.color.orange)
//            }
//        }
//        binding.imageHeroDecorationBuild.setOnClickListener {
//            equipmentClick = EquipmentType.DECORATION
//            findNavController().navigate(
//                R.id.equipmentFragment,
//                EquipmentFragment.newInstance(equipmentClick = equipmentClick)
//            )
//        }
    }

    private fun setupStatsAdapter() {
//        val list = listOf(
//            R.array.stats_in_body,
//            R.array.stats_in_legs,
//            R.array.stats_in_sphere,
//            R.array.stats_in_rope
//        )
//        mStatsAdapter = CreateBuildHeroStatsAdapter(object : CreateBuildHeroStatsListener {
//            override fun onSpinnerItemSelected(adapterPosition: Int, selectedItemPosition: Int) {
//                val selectedValue = list[adapterPosition]
//                viewModel.changeStatOnEquipment(
//                    adapterPosition,
//                    resources.getStringArray(selectedValue).toList()[selectedItemPosition]
//                )
//            }
//        })
//        mStatsAdapter.list = list
    }

    private fun setupStatsRecyclerView() {
//        binding.recyclerStatsEquipmentCreateBuild.apply {
//            layoutManager = LinearLayoutManager(requireActivity())
//            adapter = mStatsAdapter
//        }
    }

    private fun setupSaveBuildButton() {
//        binding.buttonSaveBuild.apply {
//            size = FloatingActionButton.SIZE_MINI
//            setOnClickListener {
//                if (idBuild == -1L) setupSaveDialog(R.string.adding_your_build, R.string.add_the_created_build)
//                else setupSaveDialog(R.string.update_your_build, R.string.update_the_build)
//            }
//        }
    }

    private fun setupSaveDialog(title: Int, message: Int) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.yes) { _, _ ->
                viewModel.saveBuild(idBuild)
            }
            .setNegativeButton(R.string.cancellation) { dialog, _ ->
                dialog.cancel()
            }
            .create()
        dialog.show()
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
//        binding.toolbarCreateBuildHero.setNavigationOnClickListener {
//            findNavController().popBackStack()
//        }
//        if (idBuild != -1L) {
//            binding.toolbarCreateBuildHero.apply {
//                title = getString(R.string.edit_build)
//                inflateMenu(R.menu.create_build_and_team_menu)
//                setOnMenuItemClickListener {
//                    when(it.itemId) {
//                        R.id.delete -> {
//                            setupDeleteDialog(R.string.delete_build, R.string.delete_the_build)
//                        }
//                    }
//                    true
//                }
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"
        private const val ARG_ID_BUILD = "id_build"

        @JvmStatic
        fun newInstance(idHero: Int = -1, idBuild: Long = -1L): Bundle {
            return bundleOf(ARG_ID_HERO to idHero, ARG_ID_BUILD to idBuild)
        }
    }
}