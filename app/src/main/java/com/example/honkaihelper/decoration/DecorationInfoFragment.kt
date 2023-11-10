package com.example.honkaihelper.decoration

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.databinding.FragmentDecorationInfoBinding
import com.example.honkaihelper.utils.loadImageWithoutScale

class DecorationInfoFragment : BaseFragment<FragmentDecorationInfoBinding>(FragmentDecorationInfoBinding::inflate) {

    private val idDecoration get() = requireArguments().getInt(ID_DECORATION)
    private val viewModel by viewModels<DecorationInfoViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.decorationInfoComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDecoration(idDecoration)
    }

    override fun setupView() {
        setupToolbar()
        getDecoration()
    }

    override fun uiStateHandle() {

    }

    private fun setupToolbar() {
        binding.toolbarInfoAboutDecoration.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getDecoration() {
        viewModel.decoration.observe(viewLifecycleOwner) {
            binding.imageDecoration.loadImageWithoutScale(it.image)
            binding.toolbarInfoAboutDecoration.title = it.title
            binding.textEffectDescriptionDecoration.text = it.description
        }
    }

    companion object {
        private const val ID_DECORATION = "id_decoration"
        fun newInject(idDecoration: Int): Bundle {
            return bundleOf(ID_DECORATION to idDecoration)
        }
    }
}