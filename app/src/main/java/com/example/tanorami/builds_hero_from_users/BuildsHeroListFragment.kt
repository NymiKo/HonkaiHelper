package com.example.tanorami.builds_hero_from_users

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.base.BaseFragment
import com.example.tanorami.builds_hero_from_users.adapter.BuildsHeroListAdapter
import com.example.tanorami.builds_hero_from_users.adapter.BuildsHeroListListener
import com.example.tanorami.builds_hero_from_users.di.BuildsHeroListUIState
import com.example.tanorami.create_build_hero.presentation.CreateBuildHeroFragment
import com.example.tanorami.databinding.FragmentBuildsHeroListBinding
import com.example.tanorami.databinding.ViewstubErrorLayoutBinding
import com.example.tanorami.utils.TOKEN
import com.example.tanorami.utils.getSharedPrefUser
import com.example.tanorami.utils.gone
import com.example.tanorami.utils.visible
import com.example.tanorami.viewing_users_build.ViewingUsersBuildFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BuildsHeroListFragment :
    BaseFragment<FragmentBuildsHeroListBinding>(FragmentBuildsHeroListBinding::inflate) {

    private val viewModel by viewModels<BuildsHeroListViewModel> { viewModelFactory }
    private lateinit var mAdapter: BuildsHeroListAdapter

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.buildsHeroListComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHero(idHero)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBuildsHeroList(idHero)
    }

    override fun setupView() {
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
        navigateToCreateBuild()
        retryLoadData()
        refreshData()
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is BuildsHeroListUIState.EMPTY -> {
                    binding.shimmerLayoutBuildsHeroList.stopShimmer()
                    binding.shimmerLayoutBuildsHeroList.gone()
                    binding.viewStubBuildsHeroEmptyList.visible()
                    if (getSharedPrefUser().getString(TOKEN, "").isNullOrEmpty()) binding.buttonCreate.gone()
                    else binding.buttonCreate.visible()
                    binding.swipeRefreshContainerBuildHero.isRefreshing = false
                }

                is BuildsHeroListUIState.ERROR -> {
                    binding.shimmerLayoutBuildsHeroList.stopShimmer()
                    binding.shimmerLayoutBuildsHeroList.gone()
                    binding.viewStubError.visible()
                    binding.swipeRefreshContainerBuildHero.isRefreshing = false
                }

                is BuildsHeroListUIState.LOADING -> {
                    binding.shimmerLayoutBuildsHeroList.startShimmer()
                    binding.shimmerLayoutBuildsHeroList.visible()
                    binding.recyclerBuildsHeroList.gone()
                    binding.buttonCreate.gone()
                    binding.viewStubError.gone()
                    binding.viewStubBuildsHeroEmptyList.gone()
                }

                is BuildsHeroListUIState.SUCCESS -> {
                    binding.shimmerLayoutBuildsHeroList.stopShimmer()
                    binding.shimmerLayoutBuildsHeroList.gone()
                    binding.recyclerBuildsHeroList.visible()
                    binding.swipeRefreshContainerBuildHero.isRefreshing = false
                    if (getSharedPrefUser().getString(TOKEN, "").isNullOrEmpty()) binding.buttonCreate.gone()
                    else binding.buttonCreate.visible()
                    mAdapter.buildsHeroList = state.buildsHeroList
                }
            }
        }
    }

    private fun setupAdapter() {
        mAdapter = BuildsHeroListAdapter(object : BuildsHeroListListener {
            override fun onClick(idBuild: Int) {
                findNavController().navigate(R.id.viewingUsersBuildFragment, ViewingUsersBuildFragment.newInstance(idBuild))
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerBuildsHeroList.adapter = mAdapter
        binding.recyclerBuildsHeroList.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun setupToolbar() {
        viewModel.hero.observe(viewLifecycleOwner) {
            binding.toolbarBuildsHeroList.title = getString(R.string.builds_for_hero, it.name)
        }
        binding.toolbarBuildsHeroList.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun navigateToCreateBuild() {
        binding.buttonCreate.size = FloatingActionButton.SIZE_MINI
        binding.buttonCreate.setOnClickListener {
            findNavController().navigate(
                R.id.createBuildHeroFragment,
                CreateBuildHeroFragment.newInstance(idHero)
            )
        }
    }

    private fun retryLoadData() {
        binding.viewStubError.setOnInflateListener { _, inflated ->
            val viewStubBinding = ViewstubErrorLayoutBinding.bind(inflated)

            viewStubBinding.buttonRetryLoadBuildsHero.setOnClickListener {
                viewModel.getBuildsHeroList(idHero)
            }
        }
    }

    private fun refreshData() {
        binding.swipeRefreshContainerBuildHero.setOnRefreshListener {
            viewModel.getBuildsHeroList(idHero)
        }
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"

        @JvmStatic
        fun newInstance(idHero: Int): Bundle {
            return bundleOf(ARG_ID_HERO to idHero)
        }
    }
}