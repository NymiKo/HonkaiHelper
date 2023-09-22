package com.example.honkaihelper.createteam

import android.content.Context
import android.widget.Toast
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
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.models.ActiveHeroInTeam
import com.example.honkaihelper.models.Hero
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.visible
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class CreateTeamFragment :
    BaseFragment<FragmentCreateTeamBinding>(FragmentCreateTeamBinding::inflate) {

    private val viewModel by viewModels<CreateTeamViewModel> { viewModelFactory }

    private lateinit var mAdapterForViewTeam: CreateTeamAdapter
    private lateinit var mAdapterHeroList: HeroListInCreateTeamAdapter
    private val heroesInTeamList = arrayListOf<Hero>()

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
            }
        }
    }

    private fun showRetryView() {
        binding.shimmerLayoutHeroesList.stopShimmer()
        binding.shimmerLayoutHeroesList.gone()
        binding.groupRetry.visible()
        binding.groupTeamView.gone()
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

    private fun setupRecyclerViewForViewTeam() {
        mAdapterForViewTeam = CreateTeamAdapter()
        mAdapterForViewTeam.mHeroInTeamList = heroesInTeamList
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
                    mAdapterForViewTeam.addHero(activeHeroInTeam.hero)
                } else {
                    mAdapterForViewTeam.removeHero(activeHeroInTeam.hero)
                }
            }
        })
        binding.recyclerHeroesList.apply {
            layoutManager = GridLayoutManager(requireActivity(), 4)
            adapter = mAdapterHeroList
            itemAnimator = null
        }
    }

    private fun setupButtonSaveTeam() {
        binding.buttonSaveTeam.setOnClickListener {
            if (mAdapterForViewTeam.mHeroInTeamList.size == 4) {
                showSaveDialog()
            } else {
                Toast.makeText(
                    requireContext(),
                    "В команде должно быть 4 героя",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showSaveDialog() {
        val dialog = AlertDialog.Builder(requireContext())
            .setMessage(R.string.add_the_created_command)
            .setPositiveButton(R.string.add) { _, _ ->
                // TODO: Добавить загрузку до подтверждения ответа с сервера
                viewModel.saveTeam(mAdapterForViewTeam.mHeroInTeamList)
                Toast.makeText(requireContext(), "Команда добавлена", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
            .setNegativeButton(R.string.cancellation) { dialog, _ ->
                dialog.cancel()
            }
            .create()

        dialog.show()
    }

    override fun onDestroyView() {
        binding.recyclerHeroesList.adapter = null
        binding.recyclerViewingCommand.adapter = null
        super.onDestroyView()
    }
}