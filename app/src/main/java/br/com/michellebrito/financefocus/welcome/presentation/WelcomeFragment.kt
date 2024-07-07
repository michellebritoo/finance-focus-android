package br.com.michellebrito.financefocus.welcome.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentWelcomeBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private val binding: FragmentWelcomeBinding by viewBinding()
    private val viewModel: WelcomeViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAuthInfo()
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() = with(binding) {
        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.welcomeToLogin)
        }
        btnSignup.setOnClickListener {
            findNavController().navigate(R.id.welcomeToSignUpFragment)
        }
    }

    private fun setupObservers() {
        viewModel.isUserAuthenticated.observe(viewLifecycleOwner) { authenticated ->
            if (authenticated) {
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }

}
