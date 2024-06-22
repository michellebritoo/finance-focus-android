package br.com.michellebrito.financefocus.passwordrecovery.presentation

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.MainActivity
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentPasswordRecoveryBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class PasswordRecoveryFragment : Fragment(R.layout.fragment_password_recovery) {
    private val binding: FragmentPasswordRecoveryBinding by viewBinding()
    private val viewModel: PasswordRecoveryViewModel by inject()

    override fun onResume() {
        super.onResume()
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() = with(binding) {
        tilEmail.editText?.addTextChangedListener { tilEmail.error = null }
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
        btnPasswordRecovery.setOnClickListener {
            viewModel.onContinueButtonPressed(tilEmail.editText?.text.toString())
        }
    }

    private fun setupObservers() = viewModel.viewState.observe(viewLifecycleOwner) { state ->
        when (state) {
            is PasswordRecoveryEvent.ShowLoading -> showLoading()
            is PasswordRecoveryEvent.HideLoading -> hideLoading()
            is PasswordRecoveryEvent.EmailInvalid -> onEmailInvalid()
            is PasswordRecoveryEvent.GenericError -> onError()
            is PasswordRecoveryEvent.SendWithSuccess -> onSuccess()
        }
    }

    private fun onEmailInvalid() {
        binding.tilEmail.error = getString(R.string.login_failure_invalid_email)
    }

    private fun showLoading() {
        binding.btnPasswordRecovery.disableButton()
        (requireActivity() as? MainActivity)?.showLoading()
    }

    private fun hideLoading() {
        binding.btnPasswordRecovery.enableButton()
        (requireActivity() as? MainActivity)?.hideLoading()
    }

    private fun onSuccess() {
        this@PasswordRecoveryFragment.view?.let {
            Snackbar.make(it, R.string.password_recovery_send_success, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun onError() {
        this@PasswordRecoveryFragment.view?.let {
            Snackbar.make(it, R.string.password_recovery_error, Snackbar.LENGTH_LONG).show()
        }
    }
}
