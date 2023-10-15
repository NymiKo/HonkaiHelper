package com.example.honkaihelper.setupteam

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentSetupTeamBinding
import com.example.honkaihelper.equipment.EquipmentFragment
import com.example.honkaihelper.equipment.KEY_DECORATION
import com.example.honkaihelper.equipment.KEY_RELIC
import com.example.honkaihelper.equipment.KEY_WEAPON
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.setupteam.adapter.SetupTeamAdapter
import com.example.honkaihelper.setupteam.adapter.SetupTeamListener
import com.example.honkaihelper.setupteam.data.model.SetupHero
import javax.inject.Inject

class SetupTeamFragment :
    BaseFragment<FragmentSetupTeamBinding>(FragmentSetupTeamBinding::inflate) {

    private val viewModel by viewModels<SetupTeamViewModel> { viewModelFactory }

    private lateinit var mAdapter: SetupTeamAdapter
    private val heroesList
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelableArrayList(ARG_HEROES_LIST, Hero::class.java)
        } else {
            requireArguments().getParcelableArrayList(ARG_HEROES_LIST)
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.setupTeamComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("equipment_key") { key, bundle ->
            val setupHero = mAdapter.heroesList[bundle.getInt("id_item")]
            setupHero.weapon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("equipment", Equipment::class.java)
            } else {
                bundle.getParcelable("equipment")
            }
            mAdapter.updateHero(setupHero)
        }
    }

    override fun setupView() {
        setupRecyclerView()
    }

    override fun uiStateHandle() {

    }

    private fun setupRecyclerView() {
        setupRecyclerViewAdapter()
        mAdapter.heroesList = heroesList!!.map { SetupHero(hero = it, level = null, weapon = null) }
        binding.apply {
            recyclerSetupTeam.layoutManager = LinearLayoutManager(requireActivity())
            recyclerSetupTeam.setHasFixedSize(true)
            recyclerSetupTeam.adapter = mAdapter
        }
    }

    private fun setupRecyclerViewAdapter() {
        mAdapter = SetupTeamAdapter(object : SetupTeamListener {
            override fun onWeaponClick(heroPath: Int, idItem: Int) {
                findNavController().navigate(
                    R.id.equipmentFragment,
                    EquipmentFragment.newInstance(heroPath, idItem, KEY_WEAPON)
                )
            }

            override fun onRelicClick(idItem: Int) {
                findNavController().navigate(
                    R.id.equipmentFragment,
                    EquipmentFragment.newInstance(idItem = idItem, equipmentClick = KEY_RELIC)
                )
            }

            override fun onDecorationClick(idItem: Int) {
                findNavController().navigate(
                    R.id.equipmentFragment,
                    EquipmentFragment.newInstance(idItem = idItem, equipmentClick = KEY_DECORATION)
                )
            }
        })
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