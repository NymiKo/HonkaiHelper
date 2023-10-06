package com.example.honkaihelper.teams

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentTeamsListBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.teams.adapter.HeroTeamsListAdapter
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.visible

class TeamsListFragment :
    BaseFragment<FragmentTeamsListBinding>(FragmentTeamsListBinding::inflate) {

    private val viewModel by viewModels<TeamsListViewModel> { viewModelFactory }

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)
    private val nameHero get() = requireArguments().getString(ARG_NAME_HERO)

    private lateinit var mAdapter: HeroTeamsListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.teamsListComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTeamsList(idHero)
    }

    override fun setupView() {
        setupToolbar()
        setupRecyclerView()
        openCreateTeamFragment()
        setupRetryButtonClickListener()
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
            }
        }
    }

    private fun showRetryView() {
        binding.groupRetry.visible()
        binding.shimmerLayoutTeamsList.stopShimmer()
        binding.shimmerLayoutTeamsList.gone()
        binding.groupList.gone()
    }

    private fun showTeamsList(teamsList: List<TeamHero>) {
        mAdapter.mTeamsHeroList = teamsList
        binding.groupList.visible()
        binding.shimmerLayoutTeamsList.stopShimmer()
        binding.shimmerLayoutTeamsList.gone()
    }

    private fun showLoading() {
        binding.shimmerLayoutTeamsList.startShimmer()
        binding.shimmerLayoutTeamsList.visible()
        binding.groupRetry.gone()
    }

    private fun setupRetryButtonClickListener() {
        binding.buttonRetry.setOnClickListener {
            viewModel.getTeamsList(idHero)
        }
    }

    private fun openCreateTeamFragment() {
        binding.buttonCreateTeam.setOnClickListener {
            findNavController().navigate(R.id.createTeamFragment)
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

                    if(dy < -6 && !binding.buttonCreateTeam.isShown) {
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
        binding.toolbarCreateTeam.title = resources.getString(R.string.team_for_hero, nameHero)
    }

    override fun onDestroyView() {
        binding.recyclerViewTeamsHero.adapter = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"
        private const val ARG_NAME_HERO = "name_hero"

        @JvmStatic
        fun newInstance(idHero: Int, nameHero: String): Bundle {
            return bundleOf(ARG_ID_HERO to idHero, ARG_NAME_HERO to nameHero)
        }
    }
}