package com.example.honkaihelper.teams

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.DialogRetryBottomSheetBinding
import com.example.honkaihelper.teams.adapter.HeroTeamsListAdapter
import com.example.honkaihelper.databinding.FragmentTeamsListBinding
import com.example.honkaihelper.di.ViewModelFactory
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.models.Hero
import com.example.honkaihelper.models.TeamHero
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.toast
import com.example.honkaihelper.utils.visible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import javax.inject.Inject

class TeamsListFragment :
    BaseFragment<FragmentTeamsListBinding>(FragmentTeamsListBinding::inflate),
    RetryBottomSheetDialog.RetryDialogCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<TeamsListViewModel> { viewModelFactory }

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)
    private val nameHero get() = requireArguments().getString(ARG_NAME_HERO)

    private lateinit var mAdapter: HeroTeamsListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.teamsListComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getTeamsList(idHero)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewState()
    }

    private fun setupViewState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            if (it.isLoading) showLoading()
            else stopShimming()
        }
        setupToolbar()
        setupRecyclerView()
        openCreateTeamFragment()
    }

    private fun showLoading() {
        startShimming()
    }

    private fun showErrorDialog()  {
        val retryBottomSheetDialog = RetryBottomSheetDialog()
        retryBottomSheetDialog.show(parentFragmentManager, RetryBottomSheetDialog.TAG)
        retryBottomSheetDialog.setCallback(this)
    }

    private fun startShimming() {
        binding.shimmerLayoutTeamsList.apply {
            startShimmer()
            visible()
        }
    }

    private fun stopShimming() {
        binding.shimmerLayoutTeamsList.stopShimmer()
        binding.shimmerLayoutTeamsList.gone()
    }

    private fun openCreateTeamFragment() {
        binding.buttonCreateTeam.setOnClickListener {
            findNavController().navigate(R.id.action_teamsListFragment_to_createTeamFragment)
        }
    }

    private fun setupRecyclerView() {
        mAdapter = HeroTeamsListAdapter()
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            mAdapter.mTeamsHeroList = state.teamsList
        }
        binding.recyclerViewTeamsHero.adapter = mAdapter
    }

    private fun setupToolbar() {
        binding.toolbarCreateTeam.title = resources.getString(R.string.team_for_hero, nameHero)
    }

    override fun onRetryClick() {
        viewModel.getTeamsList(idHero)
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