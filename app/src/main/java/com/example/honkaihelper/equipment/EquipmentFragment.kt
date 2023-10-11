package com.example.honkaihelper.equipment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentEquipmentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EquipmentFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentEquipmentBinding? = null
    private val binding get() = _binding!!

    private val heroPath get() = requireArguments().getInt(ARG_HERO_PATH, 1)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquipmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private const val ARG_HERO_PATH = "path"

        @JvmStatic
        fun newInstance(heroPath: Int): Bundle {
            return bundleOf(ARG_HERO_PATH to heroPath)
        }
    }
}