package com.example.tanorami.heroes

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.base.BaseFragment
import com.example.tanorami.databinding.FragmentHeroesListBinding
import com.example.tanorami.heroes.adapter.HeroesListActionListener
import com.example.tanorami.heroes.adapter.HeroesListAdapter
import com.example.tanorami.heroes.data.model.Hero
import com.example.tanorami.info_about_hero.InfoAboutHeroFragment
import com.example.tanorami.load_data.DATA_UPLOADED_KEY
import com.example.tanorami.utils.getSharedPrefToken
import com.example.tanorami.utils.gone
import com.example.tanorami.utils.loadWithPlaceholder
import com.example.tanorami.utils.uppercaseFirstChar
import com.example.tanorami.utils.visible
import com.google.android.material.card.MaterialCardView
import com.google.android.material.transition.MaterialFadeThrough

class HeroesListFragment :
    BaseFragment<FragmentHeroesListBinding>(FragmentHeroesListBinding::inflate) {

    private val viewModel by viewModels<HeroesListViewModelImpl> { viewModelFactory }
    private lateinit var mAdapterRecyclerView: HeroesListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.heroesListComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reenterTransition = MaterialFadeThrough().apply {
            duration = 500
        }
        setFragmentResultListener(DATA_UPLOADED_KEY) { _, bundle ->
            if (bundle.getBoolean(ARG_DATA_UPLOADED)) viewModel.getHeroesList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
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
    }

    private fun showLoading() {
        binding.recyclerViewHeroes.gone()
        binding.groupRetry.gone()
    }

    private fun showHeroesList(heroesList: List<Hero>) {
        mAdapterRecyclerView.mHeroesList = heroesList
        binding.recyclerViewHeroes.visible()
        binding.groupRetry.gone()
    }

    private fun setupRetryButtonClickListener() {
        binding.buttonRetry.setOnClickListener {
            findNavController().navigate(R.id.loadDataFragment)
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
                        it.filter { hero ->
                            hero.name.contains(
                                newText?.lowercase()?.uppercaseFirstChar() as CharSequence
                            )
                        }
                }
                return false
            }
        })

        binding.heroesListToolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.settings -> findNavController().navigate(R.id.settingsFragment)
            }
            true
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewHeroes.setHasFixedSize(true)
        binding.recyclerViewHeroes.layoutManager = GridLayoutManager(requireContext(), 2)
        mAdapterRecyclerView = HeroesListAdapter(object : HeroesListActionListener {
            override fun onClick(hero: Hero, view: MaterialCardView) {
                findNavController().navigate(
                    R.id.action_heroesListFragment_to_infoAboutHeroFragment,
                    InfoAboutHeroFragment.newInstance(hero.id),
                    null
                )
            }
        })
        binding.recyclerViewHeroes.adapter = mAdapterRecyclerView
    }

    override fun onDestroyView() {
        binding.recyclerViewHeroes.adapter = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_DATA_UPLOADED = "path"

        @JvmStatic
        fun newInstance(dataUploaded: Boolean): Bundle {
            return bundleOf(
                ARG_DATA_UPLOADED to dataUploaded
            )
        }
    }
}