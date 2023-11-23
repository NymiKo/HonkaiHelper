package com.example.honkaihelper.createteam

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.createteam.adapter.CreateTeamAdapter
import com.example.honkaihelper.createteam.adapter.HeroListInCreateTeamAdapter
import com.example.honkaihelper.createteam.adapter.HeroListInCreateTeamListener
import com.example.honkaihelper.databinding.FragmentCreateTeamBinding
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.createteam.data.model.ActiveHeroInTeam
import com.example.honkaihelper.setupteam.SetupTeamFragment
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.toast
import com.example.honkaihelper.utils.visible
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateTeamFragment :
    BaseFragment<FragmentCreateTeamBinding>(FragmentCreateTeamBinding::inflate) {

    private val viewModel by viewModels<CreateTeamViewModel> { viewModelFactory }

    private lateinit var mAdapterForViewTeam: CreateTeamAdapter
    private lateinit var mAdapterHeroList: HeroListInCreateTeamAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.createTeamComponent().create()
            .inject(this)
    }

    override fun setupView() {
        setupButtonSaveTeam()
        setupRecyclerViewForViewTeam()
        setupRecyclerViewHeroList()
        setupRetryButtonClickListener()
        selectHero()
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is CreateTeamUIState.ERROR -> {
                    showRetryView()
                }
                is CreateTeamUIState.LOADING -> {
                    showLoading()
                }
                is CreateTeamUIState.SUCCESS -> {
                    showCreateTeamView(it.heroesList)
                }
                is CreateTeamUIState.ERROR_TEAM_CREATION -> {
                    showErrorTeamCreation(it.message)
                }
                is CreateTeamUIState.SUCCESS_TEAM_CREATION -> {
                    successCreatingTeam()
                }
                is CreateTeamUIState.LOADING_TEAM_CREATION -> {
                    showCommandCreationLoading()
                }
            }
        }
    }

    private fun showRetryView() {
        binding.shimmerLayoutHeroesList.stopShimmer()
        binding.shimmerLayoutHeroesList.gone()
        binding.groupRetry.visible()
        binding.groupTeamView.gone()
        binding.progressTeamCreation.gone()
    }

    private fun showCreateTeamView(heroesList: List<ActiveHeroInTeam>) {
        mAdapterHeroList.mHeroList = heroesList
        binding.shimmerLayoutHeroesList.stopShimmer()
        binding.shimmerLayoutHeroesList.gone()
        binding.groupCreateTeam.visible()
    }

    private fun showLoading() {
        binding.groupRetry.gone()
        binding.groupTeamView.visible()
        binding.shimmerLayoutHeroesList.startShimmer()
        binding.shimmerLayoutHeroesList.visible()
    }

    private fun setupRetryButtonClickListener() {
        binding.buttonRetry.setOnClickListener {
            viewModel.getHeroesList()
        }
    }

    private fun showCommandCreationLoading() {
        binding.progressTeamCreation.visible()
        binding.groupCreateTeam.gone()
    }

    private fun successCreatingTeam() {
        toast(requireActivity(), R.string.team_has_been_added)
        findNavController().popBackStack()
    }

    private fun showErrorTeamCreation(message: Int) {
        binding.progressTeamCreation.gone()
        binding.groupCreateTeam.visible()
        toast(requireActivity(), message)
    }

    private fun setupRecyclerViewForViewTeam() {
        mAdapterForViewTeam = CreateTeamAdapter()
        viewModel.heroListInTeam.observe(viewLifecycleOwner) {
            mAdapterForViewTeam.mHeroListInTeam = it
        }
        val layoutManager = FlexboxLayoutManager(requireActivity()).apply {
            justifyContent = JustifyContent.CENTER
            flexDirection = FlexDirection.ROW
        }
        binding.recyclerViewingCommand.layoutManager = layoutManager
        binding.recyclerViewingCommand.adapter = mAdapterForViewTeam
    }

    private fun setupRecyclerViewHeroList() {
        mAdapterHeroList = HeroListInCreateTeamAdapter(object : HeroListInCreateTeamListener {
            override fun onClick(activeHeroInTeam: ActiveHeroInTeam) {
                if (!activeHeroInTeam.active) {
                    viewModel.addHeroInTeam(activeHeroInTeam)
                } else {
                    viewModel.removeHeroFromTeam(activeHeroInTeam)
                }
            }
        })
        binding.recyclerHeroesList.apply {
            layoutManager = GridLayoutManager(requireActivity(), 4)
            adapter = mAdapterHeroList
            itemAnimator = null
        }
    }

    private fun selectHero() {
        viewModel.selectedHero.observe(viewLifecycleOwner) {
            if (it != null) mAdapterHeroList.selectHero(it)
        }
    }

    private fun setupButtonSaveTeam() {
        binding.buttonGoSetupTeam.size = FloatingActionButton.SIZE_MINI
        binding.buttonGoSetupTeam.setOnClickListener {
            if (mAdapterForViewTeam.mHeroListInTeam.size == 4) {

            } else {
                toast(requireActivity(), R.string.should_be_4_heroes_in_the_team)
            }
        }
    }

    override fun onDestroyView() {
        binding.recyclerHeroesList.adapter = null
        binding.recyclerViewingCommand.adapter = null
        super.onDestroyView()
    }
}