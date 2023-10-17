package com.example.honkaihelper.create_build_hero

import android.os.Build
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.R
import com.example.honkaihelper.builds_hero.BuildsHeroListFragment
import com.example.honkaihelper.create_build_hero.adapter.CreateBuildEquipmentAdapter
import com.example.honkaihelper.create_build_hero.adapter.CreateBuildEquipmentListener
import com.example.honkaihelper.databinding.FragmentCreateBuildHeroBinding
import com.example.honkaihelper.equipment.EquipmentFragment
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.utils.backgroundHero
import com.example.honkaihelper.utils.toast

class CreateBuildHeroFragment :
    BaseFragment<FragmentCreateBuildHeroBinding>(FragmentCreateBuildHeroBinding::inflate) {

    lateinit var mAdapterWeapon: CreateBuildEquipmentAdapter

    private val hero get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requireArguments().getParcelable(ARG_HERO, Hero::class.java)
    } else {
        requireArguments().getParcelable(ARG_HERO)
    }

    override fun setupView() {
        loadHero()
        setupAdapter()
        setupRecyclerViewWeapon()
    }

    override fun uiStateHandle() {

    }

    private fun loadHero() {
        binding.apply {
            imageHeroAvatarInCreateBuildr.backgroundHero(hero!!)
            textHeroNameInCreateBuild.text = hero?.name
        }
    }

    private fun setupAdapter() {
        mAdapterWeapon = CreateBuildEquipmentAdapter(object : CreateBuildEquipmentListener {
            override fun onAddEquipmentClick() {
                toast(requireActivity(), R.string.weapon)
            }
        })
    }

    private fun setupRecyclerViewWeapon() {
        binding.recyclerWeaponInCreateBuild.adapter = mAdapterWeapon
        binding.recyclerWeaponInCreateBuild.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
    }

    companion object {
        private const val ARG_HERO = "hero"

        @JvmStatic
        fun newInstance(hero: Hero?): Bundle {
            return bundleOf(ARG_HERO to hero)
        }
    }
}