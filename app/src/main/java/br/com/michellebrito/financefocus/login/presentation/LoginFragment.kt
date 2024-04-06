package br.com.michellebrito.financefocus.login.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentLoginBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        tvCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.loginToSignUp)
        }
        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.loginToHome)
        }
    }
}