package com.example.tanorami.auth.registration

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.databinding.FragmentRegistrationBinding
import com.example.tanorami.base.BaseFragment
import com.example.tanorami.utils.gone
import com.example.tanorami.utils.toast
import com.example.tanorami.utils.visible

class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private val viewModel by viewModels<RegistrationViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.registrationComponent().create()
            .inject(this)
    }

    override fun setupView() {
        binding.buttonRegister.setOnClickListener {
            viewModel.registration(
                binding.editTextLogin.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is RegistrationUiState.EMPTY_LOGIN -> {
                    showErrorLogin(R.string.empty_login)
                    showButtonRegister()
                }

                is RegistrationUiState.EMPTY_PASSWORD -> {
                    showErrorPassword(R.string.empty_password)
                    showButtonRegister()
                }

                is RegistrationUiState.ERROR -> {
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                    showButtonRegister()
                }

                is RegistrationUiState.IDLE -> {
                    hideError()
                }

                is RegistrationUiState.INCORRECT_PASSWORD -> {
                    showErrorPassword(R.string.incorrect_password)
                    showButtonRegister()
                }

                is RegistrationUiState.LOADING -> {
                    showProgress()
                }

                is RegistrationUiState.SUCCESS -> {
                    toast(requireActivity(), R.string.registration_success)
                    findNavController().popBackStack()
                }

                is RegistrationUiState.INCORRECT_LOGIN -> {
                    showErrorLogin(R.string.incorrect_login)
                    showButtonRegister()
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

    private fun showErrorLogin(error: Int) {
        binding.editLayoutLogin.isErrorEnabled = true
        binding.editLayoutLogin.error = getString(error)
    }

    private fun showButtonRegister() {
        binding.buttonRegister.visible()
        binding.progressLogin.gone()
    }

    private fun showProgress() {
        binding.progressLogin.visible()
        binding.buttonRegister.gone()
    }

}