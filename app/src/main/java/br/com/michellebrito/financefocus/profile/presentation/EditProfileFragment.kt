package br.com.michellebrito.financefocus.profile.presentation

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.MainActivity
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentEditProfileBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {
    private val binding: FragmentEditProfileBinding by viewBinding()
    private val viewModel: EditProfileViewModel by inject()

    override fun onResume() {
        super.onResume()
        setupListeners()
        subscribeViewModelEvents()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
        tilEmail.editText?.addTextChangedListener { tilEmail.error = null }
        tilName.editText?.addTextChangedListener { tilName.error = null }
        btnContinue.setOnClickListener {
            viewModel.onContinueClicked(
                name = tilName.editText?.text.toString(),
                email = tilEmail.editText?.text.toString()
            )
        }
    }

    private fun subscribeViewModelEvents() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is EditProfileEvent.ShowLoading -> showLoading()
                is EditProfileEvent.HideLoading -> hideLoading()
                is EditProfileEvent.OnSuccess -> onSuccess()
                is EditProfileEvent.ShowError -> showError()
            }
        }
    }

    private fun showLoading() = (requireActivity() as MainActivity).showLoading()

    private fun hideLoading() = (requireActivity() as MainActivity).hideLoading()

    private fun onSuccess() = findNavController().navigate(R.id.profileFragment)

    private fun showError() {
        this@EditProfileFragment.view?.let {
            Snackbar.make(it, R.string.cannot_proceed_generic_error, Snackbar.LENGTH_LONG).show()
        }
    }
}
