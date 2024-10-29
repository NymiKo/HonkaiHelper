package com.example.tanorami.auth.login.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.tanorami.auth.login.presentation.LoginViewModel
import com.example.tanorami.core.theme.AppTheme
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        (requireActivity().application as App).appComponent.loginComponent().create()
//            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {

                }
            }
        }
    }
}