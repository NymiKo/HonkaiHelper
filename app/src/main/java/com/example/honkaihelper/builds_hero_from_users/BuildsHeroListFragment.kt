package com.example.honkaihelper.builds_hero_from_users

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.builds_hero_from_users.adapter.BuildsHeroListAdapter
import com.example.honkaihelper.create_build_hero.CreateBuildHeroFragment
import com.example.honkaihelper.databinding.FragmentBuildsHeroListBinding
import com.example.honkaihelper.base.BaseFragment

class BuildsHeroListFragment :
    BaseFragment<FragmentBuildsHeroListBinding>(FragmentBuildsHeroListBinding::inflate) {

    private val viewModel by viewModels<BuildsHeroListViewModel> { viewModelFactory }
    private lateinit var mAdapter: BuildsHeroListAdapter

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.buildsHeroListComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getBuildsHeroList(idHero)
        viewModel.getHero(idHero)
    }

    override fun setupView() {
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
        navigateToCreateBuild()
    }

    override fun uiStateHandle() {

    }

    private fun setupAdapter() {
        mAdapter = BuildsHeroListAdapter()
        viewModel.buildsHeroList.observe(viewLifecycleOwner) {
            mAdapter.buildsHeroList = it
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerBuildsHeroList.adapter = mAdapter
        binding.recyclerBuildsHeroList.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun setupToolbar() {
        viewModel.hero.observe(viewLifecycleOwner) {
            binding.toolbarBuildsHeroList.title = getString(R.string.builds_for_hero, it.name)
        }
    }

    private fun navigateToCreateBuild() {
        binding.buttonCreate.setOnClickListener {
            findNavController().navigate(R.id.createBuildHeroFragment, CreateBuildHeroFragment.newInstance(idHero))
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