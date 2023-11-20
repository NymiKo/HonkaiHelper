package com.example.honkaihelper.equipment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.databinding.FragmentEquipmentBinding
import com.example.honkaihelper.equipment.adapter.EquipmentAdapter
import com.example.honkaihelper.equipment.adapter.EquipmentListener
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.utils.getParcelable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class EquipmentFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<EquipmentViewModel> { viewModelFactory }

    private var _binding: FragmentEquipmentBinding? = null
    private val binding get() = _binding!!

    private val heroPath get() = requireArguments().getInt(ARG_HERO_PATH, 1)
    private val idItem get() = requireArguments().getInt(ARG_ID_ITEM, 1)
    private val equipmentClick get() = getParcelable(ARG_EQUIPMENT_CLICK, EquipmentType::class.java)
    private lateinit var mAdapter: EquipmentAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.equipmentComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getEquipmentByKey()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquipmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupAdapter()
        setupRecyclerView()
        setEquipmentList()
    }

    private fun getEquipmentByKey() {
        when (equipmentClick) {
            EquipmentType.WEAPON -> {
                viewModel.getWeapons(heroPath)
            }

            EquipmentType.RELIC -> {
                viewModel.getRelics()
            }

            EquipmentType.DECORATION -> {
                viewModel.getDecorations()
            }
            else -> {
                viewModel.getWeapons(heroPath)
            }
        }
    }

    private fun setupAdapter() {
        mAdapter = EquipmentAdapter(object : EquipmentListener {
            override fun onClick(equipment: Equipment) {
                Log.e("EQUIPMENT_CLICK", equipment.toString())
                setFragmentResult("equipment_key", bundleOf(ARG_ID_ITEM to idItem, ARG_EQUIPMENT to equipment))
                findNavController().popBackStack()
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerEquipment.apply {
            layoutManager = GridLayoutManager(requireActivity(), 4)
            adapter = mAdapter
            itemAnimator = null
        }
    }

    private fun setEquipmentList() {
        viewModel.equipmentList.observe(viewLifecycleOwner) {
            mAdapter.mEquipmentList = it
        }
    }

    companion object {
        private const val ARG_HERO_PATH = "path"
        private const val ARG_ID_ITEM = "id_item"
        private const val ARG_EQUIPMENT_CLICK = "equipment_click"
        private const val ARG_EQUIPMENT = "equipment"

        @JvmStatic
        fun newInstance(heroPath: Int = 0, idItem: Int = 0, equipmentClick: EquipmentType): Bundle {
            return bundleOf(
                ARG_HERO_PATH to heroPath,
                ARG_ID_ITEM to idItem,
                ARG_EQUIPMENT_CLICK to equipmentClick
            )
        }
    }
}