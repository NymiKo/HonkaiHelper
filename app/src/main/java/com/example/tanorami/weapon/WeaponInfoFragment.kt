package com.example.tanorami.weapon

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.base.BaseFragment
import com.example.tanorami.databinding.FragmentWeaponInfoBinding
import com.example.tanorami.utils.load
import com.example.tanorami.utils.loadImageWithoutScale
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
            drawingViewId = R.id.navHostFragment
            duration = 500
            scrimColor = Color.TRANSPARENT
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