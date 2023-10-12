package com.example.honkaihelper.equipment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentEquipmentBinding
import com.example.honkaihelper.utils.toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

const val KEY_WEAPON = "weapon"
const val KEY_RELIC = "relic"
const val KEY_DECORATION = "decoration"

class EquipmentFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentEquipmentBinding? = null
    private val binding get() = _binding!!

    private val heroPath get() = requireArguments().getInt(ARG_HERO_PATH, 1)
    private val idItem get() = requireArguments().getInt(ARG_ID_ITEM, 1)
    private val equipmentClick get() = requireArguments().getString(ARG_EQUIPMENT, KEY_WEAPON)

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
        when(equipmentClick) {
            KEY_WEAPON -> {
                Toast.makeText(requireActivity(), "WEAPON", Toast.LENGTH_SHORT).show()
            }
            KEY_RELIC -> {
                Toast.makeText(requireActivity(), "RELIC", Toast.LENGTH_SHORT).show()
            }
            KEY_DECORATION -> {
                Toast.makeText(requireActivity(), "DECORATION", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val ARG_HERO_PATH = "path"
        private const val ARG_ID_ITEM = "id_item"
        private const val ARG_EQUIPMENT = "equipment"

        @JvmStatic
        fun newInstance(heroPath: Int = 1, idItem: Int, equipmentClick: String): Bundle {
            return bundleOf(ARG_HERO_PATH to heroPath, ARG_ID_ITEM to idItem, ARG_EQUIPMENT to equipmentClick)
        }
    }
}