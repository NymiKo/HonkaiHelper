package com.example.honkaihelper.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.honkaihelper.R
import com.example.honkaihelper.adapters.heroes_list.HeroesListActionListener
import com.example.honkaihelper.adapters.heroes_list.HeroesListAdapter
import com.example.honkaihelper.databinding.FragmentHeroesListBinding
import com.example.honkaihelper.models.Hero

class HeroesListFragment : BaseFragment<FragmentHeroesListBinding>(FragmentHeroesListBinding::inflate) {

    private lateinit var mAdapterRecyclerView: HeroesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewHeroes.setHasFixedSize(true)
        binding.recyclerViewHeroes.layoutManager = GridLayoutManager(requireContext(), 3)
        mAdapterRecyclerView = HeroesListAdapter(object : HeroesListActionListener {
            override fun onClick(idHero: Int, nameHero: String) {
                findNavController().navigate(R.id.action_heroesListFragment_to_teamsListFragment, TeamsListFragment.newInstance(idHero, nameHero))
            }
        })
        mAdapterRecyclerView.mHeroesList = listOf(
            Hero(0, "Блэйд", "https://static.wikia.nocookie.net/honkai-star-rail/images/4/47/Персонаж_Блэйд_Иконка.png/revision/latest?cb=20230721132650&path-prefix=ru", true),
            Hero(1, "Цзинь Юань", "https://i.ibb.co/fMwpQCr/Upscales-ai-1693505854653.jpg", true),
            Hero(2, "Сервал", "https://static.wikia.nocookie.net/honkai-star-rail/images/f/f3/Персонаж_Сервал_Иконка.png/revision/latest?cb=20230219133911&path-prefix=ru", false)
        )
        binding.recyclerViewHeroes.adapter = mAdapterRecyclerView
    }

    override fun onDestroyView() {
        binding.recyclerViewHeroes.adapter = null
        super.onDestroyView()
    }
}