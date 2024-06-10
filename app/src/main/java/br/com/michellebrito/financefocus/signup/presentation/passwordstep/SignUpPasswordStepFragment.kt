package br.com.michellebrito.financefocus.signup.presentation.passwordstep

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.MainActivity
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentSignUpPasswordStepBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class SignUpPasswordStepFragment : Fragment(R.layout.fragment_sign_up_password_step) {
    private val binding: FragmentSignUpPasswordStepBinding by viewBinding()
    private val viewModel: SignUpPasswordViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailArgument = arguments?.getString("email").orEmpty()
        viewModel.onInit(emailArgument)

        setupListeners()
        setupObservers()
    }

    private fun setupListeners() = with(binding) {
        tilPassword.editText?.addTextChangedListener { tilPassword.error = null }
        tilConfirmPassword.editText?.addTextChangedListener { tilConfirmPassword.error = null }
        btnCreateAccount.setOnClickListener { onButtonClicked() }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {state ->
            when (state) {
                is SignUpPasswordEvent.PasswordInvalid -> showPasswordInvalidFeedback()
                is SignUpPasswordEvent.ConfirmPasswordInvalid -> showConfirmPasswordInvalidFeedback()
                is SignUpPasswordEvent.PasswordNotEquals -> showNotEqualsPasswordFeedback()
                is SignUpPasswordEvent.ShowLoading -> showLoading()
                is SignUpPasswordEvent.HideLoading -> hideLoading()
                is SignUpPasswordEvent.RegisterResult -> onCreateAccountResult(state.isSuccess)
            }
        }
    }

    private fun showPasswordInvalidFeedback() {
        binding.tilPassword.error = getString(R.string.signup_failure_invalid_password)
    }

    private fun showConfirmPasswordInvalidFeedback() {
        binding.tilConfirmPassword.error = getString(R.string.signup_failure_invalid_password)
    }

    private fun showNotEqualsPasswordFeedback() {
        binding.tilConfirmPassword.error = getString(R.string.signup_failure_not_equals_password)
    }

    private fun showLoading() {
        (requireActivity() as MainActivity).showLoading()
    }

    private fun hideLoading() {
        (requireActivity() as MainActivity).hideLoading()
    }

    private fun onCreateAccountResult(isSuccess: Boolean) {
        if (isSuccess) navigateToHome() else showGenericFeedback()
    }

    private fun showGenericFeedback() {
        this@SignUpPasswordStepFragment.view?.let {
            Snackbar.make(it, R.string.signup_confirm_failure_generic, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.homeFragment)
    }

    private fun onButtonClicked() = with(binding) {
        clearErrorState()
        viewModel.onCreateAccountButtonClicked(
            tilPassword.editText?.text.toString(),
            tilConfirmPassword.editText?.text.toString()
        )
    }

    private fun clearErrorState() = binding.apply {
        tilPassword.error = null
        tilConfirmPassword.error = null
    }
}
