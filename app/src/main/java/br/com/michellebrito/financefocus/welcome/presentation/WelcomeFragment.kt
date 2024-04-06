package br.com.michellebrito.financefocus.welcome.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentWelcomeBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private val binding: FragmentWelcomeBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.welcomeToLogin)
        }
        btnSignup.setOnClickListener {
            findNavController().navigate(R.id.welcomeToSignUpFragment)
        }
    }
}
