package com.example.honkaihelper.load_data

import android.content.Context
import androidx.fragment.app.viewModels
import com.example.honkaihelper.App
import com.example.honkaihelper.databinding.FragmentLoadDataBinding
import com.example.honkaihelper.fragments.BaseFragment

class LoadDataFragment : BaseFragment<FragmentLoadDataBinding>(FragmentLoadDataBinding::inflate) {

    private val viewModel by viewModels<LoadDataViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.loadDataComponent().create().inject(this)
    }

    override fun setupView() {

    }

    override fun uiStateHandle() {

    }
}