package com.example.honkaihelper.load_data

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentLoadDataBinding
import com.example.honkaihelper.equipment.EquipmentFragment
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.HeroesListFragment

const val DATA_UPLOADED_KEY = "data_uploaded"

class LoadDataFragment : BaseFragment<FragmentLoadDataBinding>(FragmentLoadDataBinding::inflate) {

    private val viewModel by viewModels<LoadDataViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.loadDataComponent().create().inject(this)
    }

    override fun setupView() {

    }

    override fun uiStateHandle() {
        viewModel.dataLoaded.observe(viewLifecycleOwner) {
            when(it) {
                true -> {
                    setFragmentResult(DATA_UPLOADED_KEY, HeroesListFragment.newInstance(it))
                    findNavController().popBackStack()
                }
                false -> {

                }
            }
        }
    }
}