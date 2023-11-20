package com.example.honkaihelper.create_build_hero

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.databinding.FragmentCreateBuildHeroBinding
import com.example.honkaihelper.equipment.EquipmentFragment
import com.example.honkaihelper.equipment.KEY_DECORATION
import com.example.honkaihelper.equipment.KEY_RELIC
import com.example.honkaihelper.equipment.KEY_WEAPON
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.utils.backgroundHero
import com.example.honkaihelper.utils.backgroundWeapon
import com.example.honkaihelper.utils.getParcelable
import com.example.honkaihelper.utils.load

class CreateBuildHeroFragment :
    BaseFragment<FragmentCreateBuildHeroBinding>(FragmentCreateBuildHeroBinding::inflate) {

    private val viewModel by viewModels<CreateBuildHeroViewModel> { viewModelFactory }
    private var pathHero = 0

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
            val equipment = getParcelable("equipment", Equipment::class.java)
            if (equipment != null) {
                when(bundle.getString("equipment_click")) {
                    KEY_WEAPON -> {
                        viewModel.addWeapon(equipment)
                    }

                    KEY_RELIC -> {
                        viewModel.addRelic(equipment)
                    }

                    KEY_DECORATION -> {

                    }
                }
            }
        }
    }

    override fun setupView() {
        getHero()
        setupImageWeapon()
        setupImageRelic()
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
            findNavController().navigate(
                R.id.equipmentFragment,
                EquipmentFragment.newInstance(pathHero, equipmentClick = KEY_WEAPON)
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
            findNavController().navigate(
                R.id.equipmentFragment,
                EquipmentFragment.newInstance(equipmentClick = KEY_RELIC)
            )
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