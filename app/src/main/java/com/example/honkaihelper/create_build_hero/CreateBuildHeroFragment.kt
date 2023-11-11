package com.example.honkaihelper.create_build_hero

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.create_build_hero.adapter.CreateBuildEquipmentAdapter
import com.example.honkaihelper.create_build_hero.adapter.CreateBuildEquipmentListener
import com.example.honkaihelper.databinding.FragmentCreateBuildHeroBinding
import com.example.honkaihelper.equipment.EquipmentFragment
import com.example.honkaihelper.equipment.KEY_WEAPON
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.utils.backgroundHero

class CreateBuildHeroFragment :
    BaseFragment<FragmentCreateBuildHeroBinding>(FragmentCreateBuildHeroBinding::inflate) {

    private val viewModel by viewModels<CreateBuildHeroViewModel> { viewModelFactory }
    private lateinit var mAdapterWeapon: CreateBuildEquipmentAdapter

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
                viewModel.addWeapon(equipment)
            }
        }
    }

    override fun setupView() {
        getHero()
        setupAdapter()
        setupRecyclerViewWeapon()
    }

    override fun uiStateHandle() {

    }

    private fun getHero() {
//        binding.apply {
//            imageHeroAvatarInCreateBuildr.backgroundHero(hero!!)
//            textHeroNameInCreateBuild.text = hero?.name
//        }
    }

    private fun setupAdapter() {
        mAdapterWeapon = CreateBuildEquipmentAdapter(object : CreateBuildEquipmentListener {
            override fun onAddEquipmentClick() {
//                findNavController().navigate(
//                    R.id.equipmentFragment,
//                    EquipmentFragment.newInstance(hero!!.path, equipmentClick = KEY_WEAPON)
//                )
            }

            override fun onRemoveEquipmentClick(position: Int) {
                viewModel.removeWeapon(position)
            }
        })
        viewModel.weaponList.observe(viewLifecycleOwner) {
            mAdapterWeapon.submitList(it)
        }
    }

    private fun setupRecyclerViewWeapon() {
        binding.recyclerWeaponInCreateBuild.adapter = mAdapterWeapon
        binding.recyclerWeaponInCreateBuild.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"

        @JvmStatic
        fun newInstance(idHero: Int): Bundle {
            return bundleOf(ARG_ID_HERO to idHero)
        }
    }
}