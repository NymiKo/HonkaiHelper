package com.example.honkaihelper.teams

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.createteam.CreateTeamFragment
import com.example.honkaihelper.databinding.FragmentTeamsListBinding
import com.example.honkaihelper.databinding.ViewstubErrorLayoutBinding
import com.example.honkaihelper.teams.adapter.HeroTeamsListAdapter
import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.utils.TOKEN
import com.example.honkaihelper.utils.getSharedPrefUser
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.visible

class TeamsListFragment :
    BaseFragment<FragmentTeamsListBinding>(FragmentTeamsListBinding::inflate) {

    private val viewModel by viewModels<TeamsListViewModel> { viewModelFactory }

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)

    private lateinit var mAdapter: HeroTeamsListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.teamsListComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getNameHero(idHero)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTeamsList(idHero)
    }

    override fun setupView() {
        setupToolbar()
        setupRecyclerView()
        setupCreateTeamButton()
        setupRetryButtonClickListener()
        refreshData()
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is TeamsUiState.LOADING -> {
                    showLoading()
                }

                is TeamsUiState.SUCCESS -> {
                    showTeamsList(it.teamsList)
                }

                is TeamsUiState.ERROR -> {
                    showRetryView()
                }

                is TeamsUiState.EMPTY -> {
                    emptyData()
                }
            }
        }
    }

    private fun emptyData() {
        binding.shimmerLayoutTeamsList.stopShimmer()
        binding.shimmerLayoutTeamsList.gone()
        binding.viewStubTeamsHeroEmptyList.visible()
        if (getSharedPrefUser().getString(TOKEN, "").isNullOrEmpty()) binding.buttonCreateTeam.gone()
        else binding.buttonCreateTeam.visible()
        binding.swipeRefreshContainerTeamsHero.isRefreshing = false
    }

    private fun showRetryView() {
        binding.viewStubError.visible()
        binding.shimmerLayoutTeamsList.stopShimmer()
        binding.shimmerLayoutTeamsList.gone()
        binding.recyclerViewTeamsHero.gone()
        binding.buttonCreateTeam.gone()
    }

    private fun showTeamsList(teamsList: List<TeamHero>) {
        mAdapter.mTeamsHeroList = teamsList
        binding.recyclerViewTeamsHero.visible()
        binding.shimmerLayoutTeamsList.stopShimmer()
        binding.shimmerLayoutTeamsList.gone()
        if (getSharedPrefUser().getString(TOKEN, "").isNullOrEmpty()) binding.buttonCreateTeam.gone()
        else binding.buttonCreateTeam.visible()
        binding.swipeRefreshContainerTeamsHero.isRefreshing = false
    }

    private fun showLoading() {
        binding.shimmerLayoutTeamsList.startShimmer()
        binding.shimmerLayoutTeamsList.visible()
        binding.viewStubError.gone()
        binding.viewStubTeamsHeroEmptyList.gone()
    }

    private fun setupRetryButtonClickListener() {
        binding.viewStubError.setOnInflateListener { _, inflated ->
            val viewStubBinding = ViewstubErrorLayoutBinding.bind(inflated)

            viewStubBinding.buttonRetryLoadBuildsHero.setOnClickListener {
                viewModel.getTeamsList(idHero)
            }
        }
    }

    private fun setupCreateTeamButton() {
        binding.buttonCreateTeam.setOnClickListener {
            findNavController().navigate(R.id.createTeamFragment, CreateTeamFragment.newInstance())
        }
    }

    private fun setupRecyclerView() {
        mAdapter = HeroTeamsListAdapter()
        binding.recyclerViewTeamsHero.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 6 && binding.buttonCreateTeam.isShown) {
                        binding.buttonCreateTeam.hide()
                    }

                    if (dy < -6 && !binding.buttonCreateTeam.isShown) {
                        binding.buttonCreateTeam.show()
                    }

                    if (!recyclerView.canScrollVertically(-1)) {
                        binding.buttonCreateTeam.show()
                    }
                }
            })
        }
    }

    private fun setupToolbar() {
        viewModel.nameHero.observe(viewLifecycleOwner) {
            binding.toolbarCreateTeam.title = getString(R.string.team_for_hero, it)
        }
        binding.toolbarCreateTeam.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun refreshData() {
        binding.swipeRefreshContainerTeamsHero.setOnRefreshListener {
            viewModel.getTeamsList(idHero)
        }
    }

    override fun onDestroyView() {
        binding.recyclerViewTeamsHero.adapter = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"

        @JvmStatic
        fun newInstance(idHero: Int): Bundle {
            return bundleOf(ARG_ID_HERO to idHero)
        }
    }
}