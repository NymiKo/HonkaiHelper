package com.example.honkaihelper.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.honkaihelper.adapters.HeroesListAdapter
import com.example.honkaihelper.adapters.HeroesListRecyclerViewAdapter
import com.example.honkaihelper.databinding.FragmentHeroesListBinding
import com.example.honkaihelper.models.Hero
import com.example.honkaihelper.utils.ZoomOutPageTransformer

class HeroesListFragment : BaseFragment<FragmentHeroesListBinding>(FragmentHeroesListBinding::inflate) {

    private lateinit var mAdapterViewPager: HeroesListAdapter
    private lateinit var mAdapterRecyclerView: HeroesListRecyclerViewAdapter
    private var viewHeroes: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupViewPager()
        setupRecyclerView()

        binding.buttonChangeView.setOnClickListener {
            viewHeroes = !viewHeroes
            changeViewHeroes()
        }
    }

    private fun changeViewHeroes() {
        if (viewHeroes) {
            binding.viewPagerHeroes.visibility = View.VISIBLE
            binding.recyclerViewHeroes.visibility = View.GONE
        } else {
            binding.recyclerViewHeroes.visibility = View.VISIBLE
            binding.viewPagerHeroes.visibility = View.GONE
        }
    }

    private fun setupViewPager() {
        mAdapterViewPager = HeroesListAdapter()

        binding.viewPagerHeroes.apply {
            setPageTransformer(ZoomOutPageTransformer())

            adapter = mAdapterViewPager.apply {
                mHeroesList = listOf(
                    Hero(0, "Вельт", "https://static.wikia.nocookie.net/honkai-star-rail/images/c/c3/Персонаж_Вельт_Сплэш-арт.png/revision/latest/scale-to-width-down/1000?cb=20230213151457&path-prefix=ru"),
                    Hero(1, "Цзинь Юань", "https://static.wikia.nocookie.net/houkai-star-rail/images/4/48/Character_Jing_Yuan_Splash_Art.png/revision/latest/scale-to-width-down/1000?cb=20230210115809"),
                    Hero(2, "Сервал", "https://static.wikia.nocookie.net/honkai-star-rail/images/5/52/Персонаж_Сервал_Сплэш-арт.png/revision/latest/scale-to-width-down/1000?cb=20230213151537&path-prefix=ru")
                )
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewHeroes.layoutManager = GridLayoutManager(requireContext(), 3)
        mAdapterRecyclerView = HeroesListRecyclerViewAdapter()
        mAdapterRecyclerView.mHeroesList = listOf(
            Hero(0, "Вельт", "https://static.wikia.nocookie.net/honkai-star-rail/images/c/c3/Персонаж_Вельт_Сплэш-арт.png/revision/latest/scale-to-width-down/1000?cb=20230213151457&path-prefix=ru"),
            Hero(1, "Цзинь Юань", "https://static.wikia.nocookie.net/houkai-star-rail/images/4/48/Character_Jing_Yuan_Splash_Art.png/revision/latest/scale-to-width-down/1000?cb=20230210115809"),
            Hero(2, "Сервал", "https://static.wikia.nocookie.net/honkai-star-rail/images/5/52/Персонаж_Сервал_Сплэш-арт.png/revision/latest/scale-to-width-down/1000?cb=20230213151537&path-prefix=ru")
        )
        binding.recyclerViewHeroes.adapter = mAdapterRecyclerView
    }

    override fun onDestroyView() {
        binding.viewPagerHeroes.adapter = null
        super.onDestroyView()
    }
}