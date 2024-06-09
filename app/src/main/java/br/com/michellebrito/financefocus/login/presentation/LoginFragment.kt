package br.com.michellebrito.financefocus.login.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentLoginBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by inject()

    override fun onResume() {
        super.onResume()
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() = with(binding) {
        tvCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.loginToSignUp)
        }
        btnLogin.setOnClickListener {
            viewModel.onLoginPressed(
                tilEmail.editText?.text.toString(),
                tilPassword.editText?.text.toString()
            )
        }
    }

    private fun setupObservers() {
        viewModel.loginResult.observe(viewLifecycleOwner) { success ->
            if (success) navigateToHome() else onLoginFailure()
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.loginToHome)
    }

    private fun onLoginFailure() {
        this@LoginFragment.view?.let {
            Snackbar.make(it, R.string.login_failure_message, Snackbar.LENGTH_LONG).show()
        }
    }
}
