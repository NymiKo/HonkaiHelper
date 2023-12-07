package com.example.tanorami.login

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.databinding.FragmentLoginBinding
import com.example.tanorami.base.BaseFragment
import com.example.tanorami.utils.TOKEN
import com.example.tanorami.utils.getSharedPrefUser
import com.example.tanorami.utils.gone
import com.example.tanorami.utils.toast
import com.example.tanorami.utils.visible

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.loginComponent().create()
            .inject(this)
    }

    override fun setupView() {
        openRegistrationFragment()
        binding.buttonLogin.setOnClickListener {
            viewModel.login(binding.editTextLogin.text.toString(), binding.editTextPassword.text.toString())
        }
    }

    override fun uiStateHandle() {
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
                    toast(requireActivity(), it.message)
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
                    findNavController().popBackStack(R.id.heroesListFragment, false)
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