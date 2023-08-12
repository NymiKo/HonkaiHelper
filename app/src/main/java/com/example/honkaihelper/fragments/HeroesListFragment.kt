package com.example.honkaihelper.fragments

import android.os.Bundle
import android.view.View
import com.example.honkaihelper.adapters.HeroesListAdapter
import com.example.honkaihelper.databinding.FragmentHeroesListBinding
import com.example.honkaihelper.models.Hero
import com.example.honkaihelper.utils.ZoomOutPageTransformer

class HeroesListFragment : BaseFragment<FragmentHeroesListBinding>(FragmentHeroesListBinding::inflate) {

    private lateinit var mAdapter: HeroesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupViewPager()
    }

    private fun setupViewPager() {
        mAdapter = HeroesListAdapter()

        binding.viewPagerHeroes.apply {
            setPageTransformer(ZoomOutPageTransformer())

            adapter = mAdapter.apply {
                mHeroesList = listOf(
                    Hero(0, "Вельт", "https://static.wikia.nocookie.net/honkai-star-rail/images/c/c3/Персонаж_Вельт_Сплэш-арт.png/revision/latest/scale-to-width-down/1000?cb=20230213151457&path-prefix=ru"),
                    Hero(1, "Цзинь Юань", "https://static.wikia.nocookie.net/houkai-star-rail/images/4/48/Character_Jing_Yuan_Splash_Art.png/revision/latest/scale-to-width-down/1000?cb=20230210115809"),
                    Hero(2, "Сервал", "https://static.wikia.nocookie.net/honkai-star-rail/images/5/52/Персонаж_Сервал_Сплэш-арт.png/revision/latest/scale-to-width-down/1000?cb=20230213151537&path-prefix=ru")
                )
            }
        }
    }

    override fun onDestroyView() {
        binding.viewPagerHeroes.adapter = null
        super.onDestroyView()
    }
}