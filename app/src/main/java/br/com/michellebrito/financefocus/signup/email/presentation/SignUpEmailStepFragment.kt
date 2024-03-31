package br.com.michellebrito.financefocus.signup.email.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentSignUpEmailStepBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class SignUpEmailStepFragment : Fragment(R.layout.fragment_sign_up_email_step) {
    private val binding: FragmentSignUpEmailStepBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        btnContinue.setOnClickListener {
            findNavController().navigate(R.id.signUpStepEmailToStepPassword)
        }
    }
}
