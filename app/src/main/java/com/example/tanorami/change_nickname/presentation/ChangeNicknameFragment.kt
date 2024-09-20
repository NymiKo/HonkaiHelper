package com.example.tanorami.change_nickname.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tanorami.App
import com.example.tanorami.core.theme.AppTheme
import javax.inject.Inject

class ChangeNicknameFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ChangeNicknameViewModel> { viewModelFactory }

    private val oldNickname get() = requireArguments().getString(ARG_NICKNAME)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.changeNicknameComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    ChangeNicknameScreen(
                        viewModel = viewModel,
                        onBack = { findNavController().navigateUp() }
                    )
                }
            }
        }
    }

    companion object {
        private const val ARG_NICKNAME = "nickname"

        fun newInject(nickname: String): Bundle {
            return bundleOf(ARG_NICKNAME to nickname)
        }
    }
}