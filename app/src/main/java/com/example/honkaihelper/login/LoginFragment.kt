package com.example.honkaihelper.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.honkaihelper.databinding.FragmentLoginBinding
import com.example.honkaihelper.fragments.BaseFragment
import javax.inject.Inject

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

    override fun setupView() {
        binding.buttonLogin.setOnClickListener {
            Toast.makeText(requireContext(), "Авторизация...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uiStateHandle() {

    }
}