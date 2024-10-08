package com.example.tanorami.teams.ui

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
import com.example.tanorami.App
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.teams.adapter.HeroTeamsListAdapter
import com.example.tanorami.teams.data.model.TeamHero
import com.example.tanorami.teams.presentation.TeamsListViewModel
import javax.inject.Inject

class TeamsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TeamsListViewModel> { viewModelFactory }

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)
    private val uid get() = requireArguments().getString(ARG_UID_TEAM)

    private lateinit var mAdapter: HeroTeamsListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.teamsListComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel.getNameHero(idHero)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    TeamsListScreen(
                        idHero = idHero,
                        uid = uid,
                        viewModel = viewModel,
                        navController = findNavController()
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.getTeamsList(idHero, uid)
    }

    fun setupView() {
        setupToolbar()
        setupRecyclerView()
        setupCreateTeamButton()
        setupRetryButtonClickListener()
        refreshData()
    }

    fun uiStateHandle() {
//        viewModel.uiState.observe(viewLifecycleOwner) {
//            when (it) {
//                is TeamsUiState.LOADING -> {
//                    showLoading()
//                }
//
//                is TeamsUiState.SUCCESS -> {
//                    showTeamsList(it.teamsList)
//                }
//
//                is TeamsUiState.ERROR -> {
//                    showRetryView()
//                }
//
//                is TeamsUiState.EMPTY -> {
//                    emptyData()
//                }
//            }
//        }
    }

    private fun emptyData() {
//        binding.shimmerLayoutTeamsList.stopShimmer()
//        binding.shimmerLayoutTeamsList.gone()
//        binding.viewStubTeamsHeroEmptyList.visible()
//        if (getSharedPrefUser().getString(TOKEN, "").isNullOrEmpty()) binding.buttonCreateTeam.gone()
//        else binding.buttonCreateTeam.visible()
//        binding.swipeRefreshContainerTeamsHero.isRefreshing = false
    }

    private fun showRetryView() {
//        binding.viewStubError.visible()
//        binding.shimmerLayoutTeamsList.stopShimmer()
//        binding.shimmerLayoutTeamsList.gone()
//        binding.recyclerViewTeamsHero.gone()
//        binding.buttonCreateTeam.gone()
    }

    private fun showTeamsList(teamsList: List<TeamHero>) {
//        mAdapter.mTeamsHeroList = teamsList
//        binding.recyclerViewTeamsHero.visible()
//        binding.shimmerLayoutTeamsList.stopShimmer()
//        binding.shimmerLayoutTeamsList.gone()
//        if (getSharedPrefUser().getString(TOKEN, "").isNullOrEmpty()) binding.buttonCreateTeam.gone()
//        else binding.buttonCreateTeam.visible()
//        binding.swipeRefreshContainerTeamsHero.isRefreshing = false
    }

    private fun showLoading() {
//        binding.shimmerLayoutTeamsList.startShimmer()
//        binding.shimmerLayoutTeamsList.visible()
//        binding.viewStubError.gone()
//        binding.viewStubTeamsHeroEmptyList.gone()
    }

    private fun setupRetryButtonClickListener() {
//        binding.viewStubError.setOnInflateListener { _, inflated ->
//            val viewStubBinding = ViewstubErrorLayoutBinding.bind(inflated)
//
//            viewStubBinding.buttonRetryLoadBuildsHero.setOnClickListener {
//                viewModel.getTeamsList(idHero, uid)
//            }
//        }
    }

    private fun setupCreateTeamButton() {
//        binding.buttonCreateTeam.setOnClickListener {
//            findNavController().navigate(R.id.createTeamFragment, CreateTeamFragment.newInstance())
//        }
    }

    private fun setupRecyclerView() {
//        mAdapter = HeroTeamsListAdapter(object : HeroTeamsListListener {
//            override fun onCopyClick() {
//                toast(requireContext(), R.string.message_uid_team_copied)
//            }
//        })
//        binding.recyclerViewTeamsHero.apply {
//            layoutManager = LinearLayoutManager(requireActivity())
//            adapter = mAdapter
//            addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    if (dy > 6 && binding.buttonCreateTeam.isShown) {
//                        binding.buttonCreateTeam.hide()
//                    }
//
//                    if (dy < -6 && !binding.buttonCreateTeam.isShown) {
//                        binding.buttonCreateTeam.show()
//                    }
//
//                    if (!recyclerView.canScrollVertically(-1)) {
//                        binding.buttonCreateTeam.show()
//                    }
//                }
//            })
//        }
    }

    private fun setupToolbar() {
//        viewModel.nameHero.observe(viewLifecycleOwner) {
//            if (uid == "") {
//                binding.toolbarCreateTeam.title = getString(R.string.team_for_hero, it)
//            } else {
//                binding.toolbarCreateTeam.title = getString(R.string.team_for_uid)
//            }
//        }
//        binding.toolbarCreateTeam.setNavigationOnClickListener {
//            findNavController().popBackStack()
//        }
    }

    private fun refreshData() {
//        binding.swipeRefreshContainerTeamsHero.setOnRefreshListener {
//            viewModel.getTeamsList(idHero, uid)
//        }
    }

    override fun onDestroyView() {
//        binding.recyclerViewTeamsHero.adapter = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"
        private const val ARG_UID_TEAM = "uid"

        @JvmStatic
        fun newInstance(idHero: Int = -1, uid: String = ""): Bundle {
            return bundleOf(ARG_ID_HERO to idHero, ARG_UID_TEAM to uid)
        }
    }
}