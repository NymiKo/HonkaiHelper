package com.example.honkaihelper.send_feedback

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.databinding.FragmentSendFeedbackBinding
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.toast
import com.example.honkaihelper.utils.visible
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SendFeedbackFragment : BaseFragment<FragmentSendFeedbackBinding>(FragmentSendFeedbackBinding::inflate) {

    private val viewModel by viewModels<SendFeedbackViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.sendFeedbackComponent().create().inject(this)
    }

    override fun setupView() {
        setupToolbar()
        setupButtonSendFeedback()
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
                is SendFeedbackUIState.ERROR -> {
                    binding.progressFeedback.gone()
                    binding.editLayoutFeedback.visible()
                    binding.buttonSendFeedback.visible()
                    toast(requireActivity(), it.message)
                }
                is SendFeedbackUIState.LOADING -> {
                    binding.editLayoutFeedback.gone()
                    binding.buttonSendFeedback.gone()
                    binding.progressFeedback.visible()
                }
                is SendFeedbackUIState.SUCCESS -> {
                    toast(requireActivity(), R.string.feedback_has_been_sent)
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbarFeedback.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupButtonSendFeedback() {
        binding.buttonSendFeedback.size = FloatingActionButton.SIZE_MINI
        binding.buttonSendFeedback.setOnClickListener {
            viewModel.sendFeedback(binding.editTextFeedback.text.toString())
        }
    }
}