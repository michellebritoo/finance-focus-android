package br.com.michellebrito.financefocus.login.presentation

import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.MainActivity
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentLoginBinding
import br.com.michellebrito.financefocus.util.extensions.orEmpty
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
        setupView()
    }

    private fun setupListeners() = with(binding) {
        tvCreateAccount.setOnClickListener { findNavController().navigate(R.id.loginToSignUp) }
        tvForgotPassword.setOnClickListener { findNavController().navigate(R.id.passwordRecoveryFragment) }
        btnLogin.setOnClickListener { onLoginButtonClicked() }
        tvCreateAccount.setOnClickListener { navigateToSignUp() }
        tilEmail.editText?.addTextChangedListener { tilEmail.error = null }
        tilPassword.editText?.addTextChangedListener { tilPassword.error = null }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginEvent.EmailInvalid -> showInvalidEmailFeedback()
                is LoginEvent.PasswordInvalid -> showInvalidPasswordFeedback()
                is LoginEvent.ShowLoading -> showLoading()
                is LoginEvent.HideLoading -> hideLoading()
                is LoginEvent.LoginResult -> onLoginResult(isSuccess = state.isSuccess)
            }
        }
    }

    private fun setupView() {
        val content = SpannableString(getString(R.string.forgot_password))
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.tvForgotPassword.text = content
    }

    private fun onLoginButtonClicked() = with(binding) {
        clearErrorState()
        viewModel.onLoginPressed(
            email = tilEmail.editText?.text.toString().orEmpty(),
            password = tilPassword.editText?.text.toString().orEmpty()
        )
    }

    private fun onLoginResult(isSuccess: Boolean) = if (isSuccess) {
        navigateToHome()
    } else {
        onLoginFailure()
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.loginToHome)
    }

    private fun navigateToSignUp() {
        findNavController().navigate(R.id.signUpFragment)
    }

    private fun clearErrorState() = with(binding) {
        tilEmail.error = null
        tilPassword.error = null
    }

    private fun showInvalidEmailFeedback() {
        binding.tilEmail.error = getString(R.string.login_failure_invalid_email)
    }

    private fun showInvalidPasswordFeedback() {
        binding.tilPassword.error = getString(R.string.login_failure_invalid_password)
    }

    private fun onLoginFailure() {
        this@LoginFragment.view?.let {
            Snackbar.make(it, R.string.login_failure_message, Snackbar.LENGTH_LONG).show()
        }
        binding.apply {
            tilEmail.error = getString(R.string.login_failure_incorrect_email)
            tilPassword.error = getString(R.string.login_failure_incorrect_password)
        }
    }

    private fun showLoading() = (requireActivity() as MainActivity).showLoading()

    private fun hideLoading() = (requireActivity() as MainActivity).hideLoading()
}
