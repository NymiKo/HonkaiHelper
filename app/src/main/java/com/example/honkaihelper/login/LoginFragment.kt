package com.example.honkaihelper.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentLoginBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.profile.ProfileFragment
import com.example.honkaihelper.teams.RetryBottomSheetDialog
import com.example.honkaihelper.utils.TOKEN
import com.example.honkaihelper.utils.getSharedPrefUser
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.setNavigationResult
import com.example.honkaihelper.utils.toast
import com.example.honkaihelper.utils.visible
import javax.inject.Inject

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.loginComponent().create()
            .inject(this)
    }

    override fun setupView() {
        uiStateHandle()
        openRegistrationFragment()
        binding.buttonLogin.setOnClickListener {
            viewModel.login(binding.editTextLogin.text.toString(), binding.editTextPassword.text.toString())
        }
    }

    private fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
                is LoginUiState.EMPTY_LOGIN -> {
                    showErrorLogin()
                    showButtonLogin()
                }
                is LoginUiState.EMPTY_PASSWORD -> {
                    showErrorPassword(R.string.empty_password)
                    showButtonLogin()
                }
                is LoginUiState.ERROR -> {
                    toast(requireActivity(), R.string.error_login)
                    showButtonLogin()
                }
                is LoginUiState.IDLE -> {
                    hideError()
                }
                is LoginUiState.INCORRECT_PASSWORD -> {
                    showErrorPassword(R.string.incorrect_password)
                    showButtonLogin()
                }
                is LoginUiState.LOADING -> {
                    showProgress()
                }
                is LoginUiState.SUCCESS -> {
                    getSharedPrefUser().edit().putString(TOKEN, it.token).apply()
                    findNavController().previousBackStackEntry?.savedStateHandle?.set("login", true)
                    findNavController().popBackStack(R.id.containerFragment, false)
                }
            }
        }
    }

    private fun showErrorPassword(message: Int) {
        binding.editLayoutPassword.isErrorEnabled = true
        binding.editLayoutPassword.error = getString(message)
    }

    private fun hideError() {
        binding.editLayoutLogin.isErrorEnabled = false
        binding.editLayoutPassword.isErrorEnabled = false
    }

    private fun showErrorLogin() {
        binding.editLayoutLogin.isErrorEnabled = true
        binding.editLayoutLogin.error = getString(R.string.empty_login)
    }

    private fun showButtonLogin() {
        binding.buttonLogin.visible()
        binding.progressLogin.gone()
        binding.textRegistration.visible()
    }

    private fun showProgress() {
        binding.progressLogin.visible()
        binding.buttonLogin.gone()
        binding.textRegistration.gone()
    }

    private fun openRegistrationFragment() {
        binding.textRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }
}