package br.com.michellebrito.financefocus.profile.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.MainActivity
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentProfileBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: ProfileFragmentViewModel by inject()

    override fun onResume() {
        super.onResume()
        setupListeners()
        setupObservers()
        viewModel.getUserDetails()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
        btnEditDetails.setOnClickListener { showInDevInfo() }
        btnDeleteAccount.setOnClickListener { showInDevInfo() }

        bottomNavigation.selectedItemId = R.id.item_profile
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_rates -> findNavController().navigate(R.id.goalDetailsFragment)
                R.id.item_goals -> findNavController().navigate(R.id.listGoalFragment)
            }
            true
        }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ProfileEvent.ShowLoading -> showLoading()
                is ProfileEvent.HideLoading -> hideLoading()
                is ProfileEvent.ShowError -> showError()
                is ProfileEvent.ShowUserInfo -> showUserInfo(
                    state.name,
                    state.email,
                    state.goals,
                    state.rates
                )
            }
        }
    }

    private fun showLoading() = (requireActivity() as MainActivity).showLoading()

    private fun hideLoading() = (requireActivity() as MainActivity).hideLoading()

    private fun showUserInfo(name: String, email: String, goals: String, rates: String) {
        with(binding) {
            tvSalutation.text = getString(R.string.profile_salutation, name)
            tdEmail.setText(getString(R.string.profile_email, email))
            tdNotifications.setText(getString(R.string.profile_notification))
            tdGoals.setText(getString(R.string.profile_goals, goals))
            tdRates.setText(getString(R.string.profile_rates, rates))
        }
    }

    private fun showError() {
        //task 35
    }

    private fun showInDevInfo() {
        this@ProfileFragment.view?.let {
            Snackbar.make(it, R.string.in_dev_explanation, Snackbar.LENGTH_LONG).show()
        }
    }
}
