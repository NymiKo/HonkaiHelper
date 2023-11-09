package com.example.honkaihelper.weapon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.databinding.FragmentWeaponInfoBinding

class WeaponInfoFragment : BaseFragment<FragmentWeaponInfoBinding>(FragmentWeaponInfoBinding::inflate) {

    private val idWeapon get() = requireArguments().getInt(ID_WEAPON)

    override fun setupView() {

    }

    override fun uiStateHandle() {

    }

    companion object {
        private const val ID_WEAPON = "id_weapon"

        fun newInstance(idWeapon: Int): Bundle {
            return bundleOf(ID_WEAPON to idWeapon)
        }
    }
}