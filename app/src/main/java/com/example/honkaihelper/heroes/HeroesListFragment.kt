package com.example.honkaihelper.heroes

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentHeroesListBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.adapter.HeroesListActionListener
import com.example.honkaihelper.heroes.adapter.HeroesListAdapter
import com.example.honkaihelper.teams.TeamsListFragment
import javax.inject.Inject

class HeroesListFragment :
    BaseFragment<FragmentHeroesListBinding>(FragmentHeroesListBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HeroesListViewModel> { viewModelFactory }
    private lateinit var mAdapterRecyclerView: HeroesListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.heroesListComponent().create()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewHeroes.setHasFixedSize(true)
        binding.recyclerViewHeroes.layoutManager = GridLayoutManager(requireContext(), 2)
        mAdapterRecyclerView = HeroesListAdapter(object : HeroesListActionListener {
            override fun onClick(idHero: Int, nameHero: String) {
                findNavController().navigate(
                    R.id.action_heroesListFragment_to_teamsListFragment,
                    TeamsListFragment.newInstance(idHero, nameHero)
                )
            }
        })
        viewModel.heroesList.observe(viewLifecycleOwner) { heroesList ->
            mAdapterRecyclerView.mHeroesList = heroesList
        }
        binding.recyclerViewHeroes.adapter = mAdapterRecyclerView
    }

    override fun onDestroyView() {
        binding.recyclerViewHeroes.adapter = null
        super.onDestroyView()
    }
}