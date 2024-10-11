package com.example.tanorami.builds_hero_from_users.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.builds_hero_from_users.adapter.BuildsHeroListAdapter
import com.example.tanorami.builds_hero_from_users.adapter.BuildsHeroListListener
import com.example.tanorami.builds_hero_from_users.presentation.BuildsHeroFromUsersViewModel
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.viewing_users_build.ViewingBuildHeroFromUserFragment
import javax.inject.Inject

class BuildsHeroFromUsersFragment :
    Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<BuildsHeroFromUsersViewModel> { viewModelFactory }
    private lateinit var mAdapter: BuildsHeroListAdapter

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.buildsHeroListComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel.getHero(idHero)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    BuildsHeroFromUsersScreen(
                        idHero = idHero,
                        viewModel = viewModel,
                        navController = findNavController(),
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.getBuildsHeroList(idHero)
    }

    fun setupView() {
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
        navigateToCreateBuild()
        retryLoadData()
        refreshData()
    }

    fun uiStateHandle() {
        //viewModel.uiState.observe(viewLifecycleOwner) { state ->
//            when (state) {
//                is BuildsHeroListUIState.EMPTY -> {
//                    binding.shimmerLayoutBuildsHeroList.stopShimmer()
//                    binding.shimmerLayoutBuildsHeroList.gone()
//                    binding.viewStubBuildsHeroEmptyList.visible()
//                    if (getSharedPrefUser().getString(TOKEN, "").isNullOrEmpty()) binding.buttonCreate.gone()
//                    else binding.buttonCreate.visible()
//                    binding.swipeRefreshContainerBuildHero.isRefreshing = false
//                }
//
//                is BuildsHeroListUIState.ERROR -> {
//                    binding.shimmerLayoutBuildsHeroList.stopShimmer()
//                    binding.shimmerLayoutBuildsHeroList.gone()
//                    binding.viewStubError.visible()
//                    binding.swipeRefreshContainerBuildHero.isRefreshing = false
//                }
//
//                is BuildsHeroListUIState.LOADING -> {
//                    binding.shimmerLayoutBuildsHeroList.startShimmer()
//                    binding.shimmerLayoutBuildsHeroList.visible()
//                    binding.recyclerBuildsHeroList.gone()
//                    binding.buttonCreate.gone()
//                    binding.viewStubError.gone()
//                    binding.viewStubBuildsHeroEmptyList.gone()
//                }
//
//                is BuildsHeroListUIState.SUCCESS -> {
//                    binding.shimmerLayoutBuildsHeroList.stopShimmer()
//                    binding.shimmerLayoutBuildsHeroList.gone()
//                    binding.recyclerBuildsHeroList.visible()
//                    binding.swipeRefreshContainerBuildHero.isRefreshing = false
//                    if (getSharedPrefUser().getString(TOKEN, "").isNullOrEmpty()) binding.buttonCreate.gone()
//                    else binding.buttonCreate.visible()
//                    mAdapter.buildsHeroList = state.buildsHeroList
//                }
//            }
       // }
    }

    private fun setupAdapter() {
        mAdapter = BuildsHeroListAdapter(object : BuildsHeroListListener {
            override fun onClick(idBuild: Long) {
                findNavController().navigate(R.id.viewingUsersBuildFragment, ViewingBuildHeroFromUserFragment.newInstance(idBuild))
            }
        })
    }

    private fun setupRecyclerView() {
//        binding.recyclerBuildsHeroList.adapter = mAdapter
//        binding.recyclerBuildsHeroList.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun setupToolbar() {
//        viewModel.hero.observe(viewLifecycleOwner) {
//            binding.toolbarBuildsHeroList.title = getString(R.string.builds_for_hero, it.name)
//        }
//        binding.toolbarBuildsHeroList.setNavigationOnClickListener {
//            findNavController().popBackStack()
//        }
    }

    private fun navigateToCreateBuild() {
//        binding.buttonCreate.size = FloatingActionButton.SIZE_MINI
//        binding.buttonCreate.setOnClickListener {
//            findNavController().navigate(
//                R.id.createBuildHeroFragment,
//                CreateBuildHeroFragment.newInstance(idHero)
//            )
//        }
    }

    private fun retryLoadData() {
//        binding.viewStubError.setOnInflateListener { _, inflated ->
//            val viewStubBinding = ViewstubErrorLayoutBinding.bind(inflated)
//
//            viewStubBinding.buttonRetryLoadBuildsHero.setOnClickListener {
//                viewModel.getBuildsHeroList(idHero)
//            }
//        }
    }

    private fun refreshData() {
//        binding.swipeRefreshContainerBuildHero.setOnRefreshListener {
//            viewModel.getBuildsHeroList(idHero)
//        }
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"

        @JvmStatic
        fun newInstance(idHero: Int): Bundle {
            return bundleOf(ARG_ID_HERO to idHero)
        }
    }
}