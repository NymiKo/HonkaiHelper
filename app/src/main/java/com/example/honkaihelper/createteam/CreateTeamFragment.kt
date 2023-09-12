package com.example.honkaihelper.createteam

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.createteam.adapter.CreateTeamAdapter
import com.example.honkaihelper.createteam.adapter.HeroListInCreateTeamAdapter
import com.example.honkaihelper.createteam.adapter.HeroListInCreateTeamListener
import com.example.honkaihelper.databinding.FragmentCreateTeamBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.models.ActiveHeroInTeam
import com.example.honkaihelper.models.Hero
import javax.inject.Inject

class CreateTeamFragment :
    BaseFragment<FragmentCreateTeamBinding>(FragmentCreateTeamBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CreateTeamViewModel>{ viewModelFactory }

    private var hero: Hero? = null
    private lateinit var mAdapterForViewTeam: CreateTeamAdapter
    private lateinit var mAdapterHeroList: HeroListInCreateTeamAdapter
    private val heroesInTeamList = arrayListOf<Hero>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.createTeamComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupButtonSaveTeam()
        setupRecyclerViewForViewTeam()
        setupRecyclerViewHeroList()
    }

    private fun setupRecyclerViewForViewTeam() {
        mAdapterForViewTeam = CreateTeamAdapter()
        mAdapterForViewTeam.mHeroInTeamList = heroesInTeamList
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
        viewModel.heroesList.observe(viewLifecycleOwner) { activeHeroInTeamList ->
            mAdapterHeroList.mHeroList = activeHeroInTeamList
        }
        binding.recyclerHeroesList.adapter = mAdapterHeroList
        binding.recyclerHeroesList.itemAnimator = null
    }

    private fun setupButtonSaveTeam() {
        binding.buttonSaveTeam.setOnClickListener {
            if (mAdapterForViewTeam.mHeroInTeamList.size == 4) {
                showSaveDialog()
            } else {
                Toast.makeText(requireContext(), "В команде должно быть 4 героя", Toast.LENGTH_SHORT).show()
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

    companion object {

        private const val ARG_PARAM1 = "param1"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateTeamFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}