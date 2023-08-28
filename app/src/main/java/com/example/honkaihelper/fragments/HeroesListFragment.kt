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
                    Hero(0, "Блэйд", "https://static.wikia.nocookie.net/honkai-star-rail/images/a/a3/Персонаж_Блэйд_Иконка_большая.png/revision/latest?cb=20230721164449&path-prefix=ru", true),
                    Hero(1, "Цзинь Юань", "https://static.wikia.nocookie.net/honkai-star-rail/images/5/5a/Персонаж_Цзин_Юань_Иконка_большая.png/revision/latest?cb=20230712153149&path-prefix=ru", true),
                    Hero(2, "Сервал", "https://static.wikia.nocookie.net/honkai-star-rail/images/0/0d/Персонаж_Сервал_Иконка_большая.png/revision/latest?cb=20230712153143&path-prefix=ru", false)
                )
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewHeroes.setHasFixedSize(true)
        binding.recyclerViewHeroes.layoutManager = GridLayoutManager(requireContext(), 3)
        mAdapterRecyclerView = HeroesListRecyclerViewAdapter()
        mAdapterRecyclerView.mHeroesList = listOf(
            Hero(0, "Блэйд>", "https://static.wikia.nocookie.net/honkai-star-rail/images/4/47/Персонаж_Блэйд_Иконка.png/revision/latest?cb=20230721132650&path-prefix=ru", true),
            Hero(1, "Цзинь Юань", "https://static.wikia.nocookie.net/honkai-star-rail/images/1/10/Персонаж_Цзин_Юань_Иконка.png/revision/latest?cb=20230219133939&path-prefix=ru", true),
            Hero(2, "Сервал", "https://static.wikia.nocookie.net/honkai-star-rail/images/f/f3/Персонаж_Сервал_Иконка.png/revision/latest?cb=20230219133911&path-prefix=ru", false)
        )
        binding.recyclerViewHeroes.adapter = mAdapterRecyclerView
    }

    override fun onDestroyView() {
        binding.viewPagerHeroes.adapter = null
        binding.recyclerViewHeroes.adapter = null
        super.onDestroyView()
    }
}