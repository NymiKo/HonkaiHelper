package com.example.honkaihelper.base_build_hero

import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.honkaihelper.databinding.FragmentBaseBuildHeroBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.data.model.Hero

class BaseBuildHeroFragment :
    BaseFragment<FragmentBaseBuildHeroBinding>(FragmentBaseBuildHeroBinding::inflate) {

    override fun setupView() {

    }

    override fun uiStateHandle() {

    }

    companion object {
        private const val ARG_HERO = "hero"

        @JvmStatic
        fun newInstance(hero: Hero): Bundle {
            return bundleOf(ARG_HERO to hero)
        }
    }
}