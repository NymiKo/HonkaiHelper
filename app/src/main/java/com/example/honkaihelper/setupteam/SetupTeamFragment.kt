package com.example.honkaihelper.setupteam

import android.os.Bundle
import com.example.honkaihelper.databinding.FragmentSetupTeamBinding
import com.example.honkaihelper.fragments.BaseFragment

class SetupTeamFragment :
    BaseFragment<FragmentSetupTeamBinding>(FragmentSetupTeamBinding::inflate) {

    override fun setupView() {

    }

    override fun uiStateHandle() {

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SetupTeamFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}