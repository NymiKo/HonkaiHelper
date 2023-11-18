package com.example.honkaihelper.weapon

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.databinding.FragmentWeaponInfoBinding
import com.example.honkaihelper.utils.load
import com.example.honkaihelper.utils.loadImageWithoutScale
import com.google.android.material.transition.MaterialContainerTransform

class WeaponInfoFragment : BaseFragment<FragmentWeaponInfoBinding>(FragmentWeaponInfoBinding::inflate) {

    private val idWeapon get() = requireArguments().getInt(ID_WEAPON)
    private val viewModel by viewModels<WeaponInfoViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.weaponInfoComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 500
        }
        viewModel.getWeapon(idWeapon)
    }

    override fun setupView() {
        setupToolbar()
        getWeapon()
    }

    override fun uiStateHandle() {

    }

    private fun setupToolbar() {
        binding.toolbarInfoAboutWeapon.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getWeapon() {
        viewModel.weapon.observe(viewLifecycleOwner) {
            binding.toolbarInfoAboutWeapon.title = it.weapon.name
            binding.imageWeapon.loadImageWithoutScale(it.weapon.image)
            binding.imagePathWeapon.load(it.path.image)
            val rarityImage = when(it.weapon.rarity) {
                0 -> R.drawable.icon_3_stars
                1 -> R.drawable.icon_4_stars
                2 -> R.drawable.icon_5_stars
                else -> R.drawable.icon_3_stars
            }
            binding.textStoryWeapon.text = it.weapon.story
            binding.textSkillDescriptionWeapon.text = it.weapon.description
            binding.imageRarityWeapon.loadImageWithoutScale(rarityImage)
        }
    }

    companion object {
        private const val ID_WEAPON = "id_weapon"

        fun newInstance(idWeapon: Int): Bundle {
            return bundleOf(ID_WEAPON to idWeapon)
        }
    }
}