package com.example.honkaihelper.registration

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentRegistrationBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.utils.TOKEN
import com.example.honkaihelper.utils.getSharedPrefUser
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.toast
import com.example.honkaihelper.utils.visible
import javax.inject.Inject

class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RegistrationViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.registrationComponent().create().inject(this)
    }

    override fun setupView() {
        uiStateHandle()
        binding.buttonRegister.setOnClickListener {
            viewModel.registration(binding.editTextLogin.text.toString(), binding.editTextPassword.text.toString())
        }
    }

    private fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
                is RegistrationUiState.EMPTY_LOGIN -> {
                    showErrorLogin()
                    showButtonRegister()
                }
                is RegistrationUiState.EMPTY_PASSWORD -> {
                    showErrorPassword(R.string.empty_password)
                    showButtonRegister()
                }
                is RegistrationUiState.ERROR -> {

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

    private fun showButtonRegister() {
        binding.buttonRegister.visible()
        binding.progressLogin.gone()
    }

    private fun showProgress() {
        binding.progressLogin.visible()
        binding.buttonRegister.gone()
    }

}