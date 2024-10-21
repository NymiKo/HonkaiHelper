package com.example.tanorami.heroes.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tanorami.App
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.heroes.adapter.HeroesListAdapter
import com.example.tanorami.heroes.data.model.Hero
import com.example.tanorami.heroes.presentation.HeroesListViewModelImpl
import com.example.tanorami.load_data.ui.DATA_UPLOADED_KEY
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
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
        val result = findNavController().currentBackStackEntry?.savedStateHandle?.getStateFlow(
            DATA_UPLOADED_KEY,
            false
        )
        lifecycleScope.launch {
            result?.collect {
                if (it) viewModel.getHeroesList()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    HeroesListScreen(viewModel = viewModel, navController = findNavController())
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    fun setupView() {
        addMenu()
        setupRecyclerView()
        setupRetryButtonClickListener()
        setupProfileButtonClickListener()
    }

    fun uiStateHandle() {
//        viewModel.uiState.observe(viewLifecycleOwner) {
//            when (it) {
//                is HeroesUiState.ERROR -> {
//                    showRetryView()
//                }
//
//                is HeroesUiState.LOADING -> {
//                    showLoading()
//                }
//
//                is HeroesUiState.SUCCESS -> {
//                    showHeroesList(it.heroesList)
//                    val token = getSharedPrefToken()
//                    if (!token.isNullOrEmpty()) {
//                        viewModel.getAvatar()
//                        binding.buttonProfile.imageTintList = null
//                        loadAvatar()
//                    }
//                }
//            }
//        }
    }

    private fun showRetryView() {
//        binding.groupRetry.visible()
//        binding.recyclerViewHeroes.gone()
    }

    private fun showLoading() {
//        binding.recyclerViewHeroes.gone()
//        binding.groupRetry.gone()
    }

    private fun showHeroesList(heroesList: List<Hero>) {
        mAdapterRecyclerView.mHeroesList = heroesList
//        binding.recyclerViewHeroes.visible()
//        binding.groupRetry.gone()
    }

    private fun setupRetryButtonClickListener() {
//        binding.buttonRetry.setOnClickListener {
//            findNavController().navigate(R.id.loadDataFragment)
//        }
    }

    private fun setupProfileButtonClickListener() {
//        binding.buttonProfile.setOnClickListener {
//            findNavController().navigate(R.id.profileFragment)
//        }
    }

    private fun loadAvatar() {
//        viewModel.avatar.observe(viewLifecycleOwner) { avatarUrl ->
//            binding.buttonProfile.loadWithPlaceholder(avatarUrl, R.drawable.ic_person)
//        }
    }

    private fun addMenu() {
//        binding.heroesListToolbar.setOnMenuItemClickListener {
//            when(it.itemId) {
//                R.id.search -> setupSearchDialog()
//                R.id.settings -> findNavController().navigate(R.id.settingsFragment)
//            }
//            true
//        }
    }

    private fun setupRecyclerView() {
//        binding.recyclerViewHeroes.setHasFixedSize(true)
//        binding.recyclerViewHeroes.layoutManager = GridLayoutManager(requireContext(), 2)
//        mAdapterRecyclerView = HeroesListAdapter(object : HeroesListActionListener {
//            override fun onClick(hero: Hero, view: MaterialCardView) {
//                findNavController().navigate(
//                    R.id.action_heroesListFragment_to_infoAboutHeroFragment,
//                    InfoAboutHeroFragment.newInstance(hero.id),
//                    null
//                )
//            }
//        })
//        binding.recyclerViewHeroes.adapter = mAdapterRecyclerView
    }

    private fun setupSearchDialog() {
//        val searchStringArray = resources.getStringArray(R.array.search_dialog)
//
//        val dialog = AlertDialog.Builder(requireContext())
//            .setItems(searchStringArray) { _, item ->
//                when(item) {
//                    0 -> {
//                        val manager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
//                        val searchItem = binding.heroesListToolbar.menu.findItem(R.id.search)
//                        val searchView = searchItem.actionView as SearchView
//                        searchView.queryHint = searchStringArray[0]
//                        searchView.setSearchableInfo(manager.getSearchableInfo(requireActivity().componentName))
//                        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                            override fun onQueryTextSubmit(query: String?): Boolean {
//                                searchView.clearFocus()
//                                searchView.setQuery("", false)
//                                searchItem.collapseActionView()
//                                viewModel.heroesList.observe(viewLifecycleOwner) {
//                                    mAdapterRecyclerView.mHeroesList = it
//                                }
//                                return true
//                            }
//
//                            override fun onQueryTextChange(newText: String?): Boolean {
//                                viewModel.heroesList.observe(viewLifecycleOwner) {
//                                    mAdapterRecyclerView.mHeroesList =
//                                        it.filter { hero ->
//                                            hero.name.contains(
//                                                newText?.lowercase()?.uppercaseFirstChar() as CharSequence
//                                            )
//                                        }
//                                }
//                                return false
//                            }
//                        })
//                    }
//
//                    1 -> {
//                        val manager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
//                        val searchItem = binding.heroesListToolbar.menu.findItem(R.id.search)
//                        val searchView = searchItem.actionView as SearchView
//                        searchView.queryHint = searchStringArray[1]
//                        searchView.setSearchableInfo(manager.getSearchableInfo(requireActivity().componentName))
//                        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                            override fun onQueryTextSubmit(query: String?): Boolean {
//                                searchView.clearFocus()
//                                searchView.setQuery("", false)
//                                searchItem.collapseActionView()
//                                findNavController().navigate(
//                                    R.id.action_heroesListFragment_to_viewingUsersBuildFragment,
//                                    ViewingBuildHeroFromUserFragment.newInstance(uid = query!!)
//                                )
//                                return true
//                            }
//
//                            override fun onQueryTextChange(newText: String?): Boolean {
//                                return false
//                            }
//                        })
//                    }
//
//                    2 -> {
//                        val manager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
//                        val searchItem = binding.heroesListToolbar.menu.findItem(R.id.search)
//                        val searchView = searchItem.actionView as SearchView
//                        searchView.queryHint = searchStringArray[2]
//                        searchView.setSearchableInfo(manager.getSearchableInfo(requireActivity().componentName))
//                        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                            override fun onQueryTextSubmit(query: String?): Boolean {
//                                searchView.clearFocus()
//                                searchView.setQuery("", false)
//                                searchItem.collapseActionView()
//                                findNavController().navigate(R.id.action_heroesListFragment_to_teamsListFragment, TeamsListFragment.newInstance(uid = query!!))
//                                return true
//                            }
//
//                            override fun onQueryTextChange(newText: String?): Boolean {
//                                return false
//                            }
//                        })
//                    }
//                }
//            }
//            .create()
//        dialog.show()
    }

    override fun onDestroyView() {
//        binding.recyclerViewHeroes.adapter = null
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