package com.example.honkaihelper.change_nickname

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.databinding.FragmentChangeNicknameBinding
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.visible

class ChangeNicknameFragment :
    BaseFragment<FragmentChangeNicknameBinding>(FragmentChangeNicknameBinding::inflate) {

    private val viewModel by viewModels<ChangeNicknameViewModel> { viewModelFactory }

    private val oldNickname get() = requireArguments().getString(ARG_NICKNAME)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.changeNicknameComponent().create()
            .inject(this)
    }

    override fun setupView() {
        setupChangeNicknameButton()
        setupToolbar()
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is ChangeNicknameUiState.ERROR -> {
                    binding.progressChangeNickname.gone()
                    binding.buttonChangeNickname.visible()
                    binding.editLayoutLogin.visible()
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                }

                is ChangeNicknameUiState.LOADING -> {
                    binding.progressChangeNickname.visible()
                    binding.buttonChangeNickname.gone()
                    binding.editLayoutLogin.gone()
                }

                is ChangeNicknameUiState.SUCCESS -> {
                    Toast.makeText(requireActivity(), R.string.nickname_changed, Toast.LENGTH_SHORT)
                        .show()
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun setupChangeNicknameButton() {
        binding.buttonChangeNickname.setOnClickListener {
            viewModel.changeNickname(oldNickname ?: "", binding.editTextLogin.text.toString())
        }
    }

    private fun setupToolbar() {
        binding.toolbarChangeNickname.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        private const val ARG_NICKNAME = "nickname"

        fun newInject(nickname: String): Bundle {
            return bundleOf(ARG_NICKNAME to nickname)
        }
    }
}