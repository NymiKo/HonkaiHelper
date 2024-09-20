package com.example.tanorami.create_build_hero.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.equipment.EquipmentFragment
import com.example.tanorami.equipment.EquipmentType
import com.example.tanorami.equipment.data.model.Equipment
import javax.inject.Inject

class CreateBuildHeroFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<CreateBuildHeroViewModel> { viewModelFactory }
    private var equipmentClick = EquipmentType.WEAPON

    private val idBuild get() = requireArguments().getLong(ARG_ID_BUILD)
    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)

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
                        viewModel.onEvent(CreateBuildHeroScreenEvents.AddWeapon(equipment))
                    }

                    EquipmentType.RELIC_TWO_PARTS -> {
                        viewModel.onEvent(CreateBuildHeroScreenEvents.AddTwoPartsRelic(equipment))
                    }

                    EquipmentType.RELIC_FOUR_PARTS -> {
                        viewModel.onEvent(CreateBuildHeroScreenEvents.AddFourPartsRelic(equipment))
                    }

                    EquipmentType.DECORATION -> {
                        viewModel.onEvent(CreateBuildHeroScreenEvents.AddDecoration(equipment))
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
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    CreateBuildHeroScreen(
                        viewModel = viewModel,
                        idBuild = idBuild,
                        idHero = idHero,
                        onEquipmentScreen = { pathHero, equipmentType ->
                            equipmentClick = equipmentType
                            findNavController().navigate(
                                R.id.equipmentFragment,
                                EquipmentFragment.newInstance(pathHero, equipmentClick = equipmentType)
                            )
                        },
                        onBack = { findNavController().navigateUp() }
                    )
                }
            }
        }
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