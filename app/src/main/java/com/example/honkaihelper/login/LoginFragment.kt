package com.example.honkaihelper.login

import android.widget.Toast
import com.example.honkaihelper.databinding.FragmentLoginBinding
import com.example.honkaihelper.fragments.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override fun setupView() {
        binding.buttonLogin.setOnClickListener {
            Toast.makeText(requireContext(), "Авторизация...", Toast.LENGTH_SHORT).show()
        }
    }


}