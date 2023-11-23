package com.example.honkaihelper.createteam

import android.content.Context
import androidx.appcompat.app.AlertDialog
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
        selectHero()
    }

    override fun uiStateHandle() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is CreateTeamUIState.ERROR_TEAM_CREATION -> {
                    showErrorTeamCreation(it.message)
                }
                is CreateTeamUIState.SUCCESS_TEAM_CREATION -> {
                    successCreatingTeam()
                }
                is CreateTeamUIState.LOADING_TEAM_CREATION -> {
                    showCommandCreationLoading()
                }
                is CreateTeamUIState.CREATING_TEAM -> {
                    viewModel.heroList.observe(viewLifecycleOwner) { heroesList ->
                        mAdapterHeroList.mHeroList = heroesList
                    }
                }
            }
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

    private fun setupSaveDialog() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.adding_a_command)
            .setMessage(R.string.add_the_created_command)
            .setPositiveButton(R.string.yes) { _, _ ->
                viewModel.saveTeam()
            }
            .setNegativeButton(R.string.cancellation) { dialog, _ ->
                dialog.cancel()
            }
            .create()
        dialog.show()
    }

    private fun setupButtonSaveTeam() {
        binding.buttonGoSetupTeam.size = FloatingActionButton.SIZE_MINI
        binding.buttonGoSetupTeam.setOnClickListener {
            if (mAdapterForViewTeam.mHeroListInTeam.size == 4) {
                setupSaveDialog()
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