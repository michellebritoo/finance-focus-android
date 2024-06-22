package br.com.michellebrito.financefocus.passwordrecovery.presentation

import androidx.fragment.app.Fragment
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentPasswordRecoveryBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject

class PasswordRecoveryFragment : Fragment(R.layout.fragment_password_recovery) {
    private val binding: FragmentPasswordRecoveryBinding by viewBinding()
    private val viewModel: PasswordRecoveryViewModel by inject()

    override fun onResume() {
        super.onResume()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        btnLogin.setOnClickListener { viewModel.sendPasswordRecovery(tilEmail.editText?.text.toString()) }
    }
}
