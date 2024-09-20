package com.example.tanorami.createteam.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.createteam.adapter.CreateTeamAdapter
import com.example.tanorami.createteam.adapter.HeroListInCreateTeamAdapter
import javax.inject.Inject

class CreateTeamFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CreateTeamViewModel> { viewModelFactory }

    private lateinit var mAdapterForViewTeam: CreateTeamAdapter
    private lateinit var mAdapterHeroList: HeroListInCreateTeamAdapter

    private val idTeam get() = requireArguments().getLong(ARG_ID_TEAM, -1)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.createTeamComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    CreateTeamScreen(
                        viewModel = viewModel,
                        idTeam = idTeam
                    )
                }
            }
        }
    }

    fun setupView() {
        setupButtonSaveTeam()
        setupRecyclerViewForViewTeam()
        setupRecyclerViewHeroList()
        selectHero()
        setupToolbar()
    }

    fun uiStateHandle() {
//        viewModel.state.observe(viewLifecycleOwner) {
//            when (it) {
//                is CreateTeamUIState.ERROR_TEAM_CREATION -> {
//                    showErrorTeamCreation(it.message)
//                }
//                is CreateTeamUIState.SUCCESS_TEAM_CREATION -> {
//                    successResultTeam(R.string.team_has_been_added)
//                }
//                is CreateTeamUIState.LOADING_TEAM_CREATION -> {
//                    showCommandCreationLoading()
//                }
//                is CreateTeamUIState.CREATING_TEAM -> {
//                    viewModel.heroList.observe(viewLifecycleOwner) { heroesList ->
//                        mAdapterHeroList.mHeroList = heroesList
//                    }
//                }
//
//                CreateTeamUIState.SUCCESS_TEAM_DELETION -> {
//                    successResultTeam(R.string.deletion_team)
//                }
//            }
//        }
    }

    private fun showCommandCreationLoading() {
//        binding.progressTeamCreation.visible()
//        binding.groupCreateTeam.gone()
    }

    private fun successResultTeam(message: Int) {
//        toast(requireActivity(), message)
//        findNavController().popBackStack()
    }

    private fun showErrorTeamCreation(message: Int) {
//        binding.progressTeamCreation.gone()
//        binding.groupCreateTeam.visible()
//        toast(requireActivity(), message)
    }

    private fun setupRecyclerViewForViewTeam() {
//        mAdapterForViewTeam = CreateTeamAdapter()
//        viewModel.heroListInTeam.observe(viewLifecycleOwner) {
//            mAdapterForViewTeam.mHeroListInTeam = it
//        }
//        val layoutManager = FlexboxLayoutManager(requireActivity()).apply {
//            justifyContent = JustifyContent.CENTER
//            flexDirection = FlexDirection.ROW
//        }
//        binding.recyclerViewingCommand.layoutManager = layoutManager
//        binding.recyclerViewingCommand.adapter = mAdapterForViewTeam
    }

    private fun setupRecyclerViewHeroList() {
//        mAdapterHeroList = HeroListInCreateTeamAdapter(object : HeroListInCreateTeamListener {
//            override fun onClick(activeHeroInTeam: ActiveHeroInTeam) {
//                if (!activeHeroInTeam.active) {
//                    viewModel.addHeroInTeam(activeHeroInTeam)
//                } else {
//                    viewModel.removeHeroFromTeam(activeHeroInTeam)
//                }
//            }
//        })
//        binding.recyclerHeroesList.apply {
//            layoutManager = GridLayoutManager(requireActivity(), 4)
//            adapter = mAdapterHeroList
//            itemAnimator = null
//        }
    }

    private fun selectHero() {
//        viewModel.selectedHero.observe(viewLifecycleOwner) {
//            if (it != null) {
//                mAdapterHeroList.selectHero(it)
//            }
//        }
    }

    private fun setupSaveDialog(title: Int, message: Int) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.yes) { _, _ ->
                //viewModel.saveTeam(idTeam)
            }
            .setNegativeButton(R.string.cancellation) { dialog, _ ->
                dialog.cancel()
            }
            .create()
        dialog.show()
    }

    private fun setupDeleteDialog(title: Int, message: Int) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.yes) { _, _ ->
                //viewModel.deleteTeam(idTeam)
            }
            .setNegativeButton(R.string.cancellation) { dialog, _ ->
                dialog.cancel()
            }
            .create()
        dialog.show()
    }

    private fun setupButtonSaveTeam() {
//        binding.buttonGoSetupTeam.size = FloatingActionButton.SIZE_MINI
//        binding.buttonGoSetupTeam.setOnClickListener {
//            if (mAdapterForViewTeam.mHeroListInTeam.size == 4) {
//                if (idTeam == -1L) setupSaveDialog(R.string.adding_a_command, R.string.add_the_created_command)
//                else setupSaveDialog(R.string.update_a_command, R.string.update_the_command)
//            } else {
//                toast(requireActivity(), R.string.should_be_4_heroes_in_the_team)
//            }
//        }
    }

    private fun setupToolbar() {
//        if (idTeam != -1L) {
//            binding.materialToolbar.title = getString(R.string.edit_team)
//            binding.materialToolbar.inflateMenu(R.menu.create_build_and_team_menu)
//            binding.materialToolbar.setOnMenuItemClickListener {
//                when(it.itemId) {
//                    R.id.copy_uid_team -> {
//                        viewModel.uidTeam.observe(viewLifecycleOwner) { uid ->
//                            val clipboard = requireContext().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
//                            val clipData: ClipData = ClipData.newPlainText("UID", uid)
//                            clipboard.setPrimaryClip(clipData)
//                            toast(requireContext(), R.string.message_uid_team_copied)
//                        }
//                    }
//                    R.id.delete -> setupDeleteDialog(R.string.delete_a_command, R.string.delete_the_command)
//                }
//                true
//            }
//        }
    }

    override fun onDestroyView() {
//        binding.recyclerHeroesList.adapter = null
//        binding.recyclerViewingCommand.adapter = null
        super.onDestroyView()
    }

    companion object {
        const val ARG_ID_TEAM = "id_team"

        fun newInstance(idTeam: Long = -1): Bundle {
            return bundleOf(ARG_ID_TEAM to idTeam)
        }
    }
}