package br.com.michellebrito.financefocus.signup.presentation.emailstep

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentSignUpEmailStepBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject

class SignUpEmailStepFragment : Fragment(R.layout.fragment_sign_up_email_step) {
    private val binding: FragmentSignUpEmailStepBinding by viewBinding()
    private val viewModel: SignUpEmailViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
        tilEmail.editText?.addTextChangedListener { tilEmail.error = null }
        tilEmail.editText?.addTextChangedListener { tilEmail.error = null }
        btnContinue.setOnClickListener {
            clearErrorState()
            viewModel.onContinueButtonPressed(
                tilEmail.editText?.text.toString(),
                tilConfirmEmail.editText?.text.toString()
            )
        }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { state ->
                when (state) {
                    is SignUpEmailEvent.EmailInvalid -> showEmailInvalidFeedback()
                    is SignUpEmailEvent.ConfirmEmailInvalid -> showConfirmEmailInvalidFeedback()
                    is SignUpEmailEvent.EmailNotEquals -> showEmailNotEqualsFeedback()
                    is SignUpEmailEvent.ValidateSuccess -> goToNextStep()
                }
            }

        }
    }

    private fun showEmailInvalidFeedback() {
        binding.tilEmail.error = getString(R.string.login_failure_invalid_email)
    }

    private fun showConfirmEmailInvalidFeedback() {
        binding.tilConfirmEmail.error = getString(R.string.login_failure_invalid_email)
    }

    private fun showEmailNotEqualsFeedback() {
        binding.tilConfirmEmail.error = getString(R.string.signup_failure_not_equals_email)
    }

    private fun clearErrorState() = with(binding) {
        tilEmail.error = null
        tilConfirmEmail.error = null
    }

    private fun goToNextStep() {
        val email = binding.tilEmail.editText?.text?.toString().orEmpty().trim()
        val action = SignUpEmailStepFragmentDirections.signUpStepEmailToStepPassword(email)
        findNavController().navigate(action)
    }
}
