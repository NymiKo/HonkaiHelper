package com.example.honkaihelper.create_build_hero

import android.os.Build
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.honkaihelper.builds_hero.BuildsHeroListFragment
import com.example.honkaihelper.databinding.FragmentCreateBuildHeroBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.utils.backgroundHero

class CreateBuildHeroFragment :
    BaseFragment<FragmentCreateBuildHeroBinding>(FragmentCreateBuildHeroBinding::inflate) {

    private val hero get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requireArguments().getParcelable(ARG_HERO, Hero::class.java)
    } else {
        requireArguments().getParcelable(ARG_HERO)
    }

    override fun setupView() {
        loadHero()
    }

    override fun uiStateHandle() {

    }

    private fun loadHero() {
        binding.apply {
            imageHeroAvatarInCreateBuildr.backgroundHero(hero!!)
            textHeroNameInCreateBuild.text = hero?.name
        }
    }

    companion object {
        private const val ARG_HERO = "hero"

        @JvmStatic
        fun newInstance(hero: Hero?): Bundle {
            return bundleOf(ARG_HERO to hero)
        }
    }
}