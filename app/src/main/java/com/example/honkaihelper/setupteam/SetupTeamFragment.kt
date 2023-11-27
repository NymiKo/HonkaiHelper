package com.example.honkaihelper.setupteam

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentSetupTeamBinding
import com.example.honkaihelper.equipment.EquipmentFragment
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.equipment.EquipmentType
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.setupteam.adapter.SetupTeamAdapter
import com.example.honkaihelper.setupteam.adapter.SetupTeamListener
import com.example.honkaihelper.utils.getParcelableArrayList

class SetupTeamFragment :
    BaseFragment<FragmentSetupTeamBinding>(FragmentSetupTeamBinding::inflate) {

    private val viewModel by viewModels<SetupTeamViewModel> { viewModelFactory }

    private lateinit var mAdapter: SetupTeamAdapter
    private val heroesList get() = getParcelableArrayList(ARG_HEROES_LIST, Hero::class.java)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.setupTeamComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("equipment_key") { key, bundle ->
            val setupHero = mAdapter.currentList[bundle.getInt("id_item")]
            setupHero.weapon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("equipment", Equipment::class.java)
            } else {
                bundle.getParcelable("equipment")
            }
            viewModel.updateHero(setupHero)
        }

        viewModel.setHeroesList(heroesList!!.toList())
    }

    override fun setupView() {
        setupRecyclerView()
    }

    override fun uiStateHandle() {

    }

    private fun setupRecyclerView() {
        setupRecyclerViewAdapter()
        binding.apply {
            recyclerSetupTeam.layoutManager = LinearLayoutManager(requireActivity())
            recyclerSetupTeam.setHasFixedSize(true)
            recyclerSetupTeam.adapter = mAdapter
        }
    }

    private fun setupRecyclerViewAdapter() {
        mAdapter = SetupTeamAdapter(object : SetupTeamListener {
            override fun onWeaponClick(heroPath: Int, idItem: Int) {
                navigateToEquipmentFragment(idItem, EquipmentType.WEAPON, heroPath)
            }

            override fun onRelicClick(idItem: Int) {
                //navigateToEquipmentFragment(idItem, EquipmentType.RELIC)
            }

            override fun onDecorationClick(idItem: Int) {
                navigateToEquipmentFragment(idItem, EquipmentType.DECORATION)
            }
        })

        viewModel.heroesList.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
    }

    private fun navigateToEquipmentFragment(
        idItem: Int,
        equipmentClick: EquipmentType,
        heroPath: Int = 1
    ) {
        findNavController().navigate(
            R.id.equipmentFragment,
            EquipmentFragment.newInstance(
                idItem = idItem,
                equipmentClick = equipmentClick,
                heroPath = heroPath
            )
        )
    }

    override fun onDestroyView() {
        binding.recyclerSetupTeam.adapter = null
        super.onDestroyView()
    }

    companion object {

        private const val ARG_HEROES_LIST = "heroes_list"

        @JvmStatic
        fun newInstance(heroesList: List<Hero>): Bundle {
            return bundleOf(ARG_HEROES_LIST to heroesList)
        }
    }
}