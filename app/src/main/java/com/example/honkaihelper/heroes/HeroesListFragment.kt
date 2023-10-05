package com.example.honkaihelper.heroes

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentHeroesListBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.adapter.HeroesListActionListener
import com.example.honkaihelper.heroes.adapter.HeroesListAdapter
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.teams.TeamsListFragment
import com.example.honkaihelper.utils.getSharedPrefToken
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.loadWithPlaceholder
import com.example.honkaihelper.utils.uppercaseFirstChar
import com.example.honkaihelper.utils.visible

class HeroesListFragment :
    BaseFragment<FragmentHeroesListBinding>(FragmentHeroesListBinding::inflate) {

    private val viewModel by viewModels<HeroesListViewModelImpl> { viewModelFactory }
    private lateinit var mAdapterRecyclerView: HeroesListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.heroesListComponent().create()
            .inject(this)
    }

    override fun setupView() {
        addMenu()
        setupRecyclerView()
        setupRetryButtonClickListener()
        setupProfileButtonClickListener()
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is HeroesUiState.ERROR -> {
                    showRetryView()
                }

                is HeroesUiState.LOADING -> {
                    showLoading()
                }

                is HeroesUiState.SUCCESS -> {
                    showHeroesList(it.heroesList)
                    val token = getSharedPrefToken()
                    if (!token.isNullOrEmpty()) {
                        viewModel.getAvatar()
                        binding.buttonProfile.imageTintList = null
                        loadAvatar()
                    }
                }
            }
        }
    }

    private fun showRetryView() {
        binding.groupRetry.visible()
        binding.recyclerViewHeroes.gone()
        binding.shimmerLayoutHeroesList.stopShimmer()
        binding.shimmerLayoutHeroesList.gone()
    }

    private fun showLoading() {
        binding.shimmerLayoutHeroesList.startShimmer()
        binding.shimmerLayoutHeroesList.visible()
        binding.recyclerViewHeroes.gone()
        binding.groupRetry.gone()
    }

    private fun showHeroesList(heroesList: List<Hero>) {
        mAdapterRecyclerView.mHeroesList = heroesList
        binding.recyclerViewHeroes.visible()
        binding.shimmerLayoutHeroesList.stopShimmer()
        binding.shimmerLayoutHeroesList.gone()
        binding.groupRetry.gone()
    }

    private fun setupRetryButtonClickListener() {
        binding.buttonRetry.setOnClickListener {
            viewModel.getHeroesList()
        }
    }

    private fun setupProfileButtonClickListener() {
        binding.buttonProfile.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }
    }

    private fun loadAvatar() {
        viewModel.avatar.observe(viewLifecycleOwner) { avatarUrl ->
            binding.buttonProfile.loadWithPlaceholder(avatarUrl, R.drawable.ic_person)
        }
    }

    private fun addMenu() {
        val manager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = binding.heroesListToolbar.menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setSearchableInfo(manager.getSearchableInfo(requireActivity().componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                viewModel.heroesList.observe(viewLifecycleOwner) {
                    mAdapterRecyclerView.mHeroesList = it
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.heroesList.observe(viewLifecycleOwner) {
                    mAdapterRecyclerView.mHeroesList =
                        it.filter { hero -> hero.name.contains(newText?.lowercase()?.uppercaseFirstChar() as CharSequence) }
                }
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerViewHeroes.setHasFixedSize(true)
        binding.recyclerViewHeroes.layoutManager = GridLayoutManager(requireContext(), 2)
        mAdapterRecyclerView = HeroesListAdapter(object : HeroesListActionListener {
            override fun onClick(idHero: Int, nameHero: String) {
                findNavController().navigate(
                    R.id.teamsListFragment,
                    TeamsListFragment.newInstance(idHero, nameHero)
                )
            }
        })
        binding.recyclerViewHeroes.adapter = mAdapterRecyclerView
    }

    override fun onDestroyView() {
        binding.recyclerViewHeroes.adapter = null
        super.onDestroyView()
    }
}