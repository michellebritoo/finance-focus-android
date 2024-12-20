package br.com.michellebrito.financefocus.profile.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
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
        btnEditDetails.setOnClickListener { findNavController().navigate(R.id.editProfileFragment) }
        btnDeleteAccount.setOnClickListener { viewModel.onDeleteAccountClicked() }
        btnEditLogout.setOnClickListener { viewModel.onLogoutClicked() }

        bottomNavigation.selectedItemId = R.id.item_profile
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_rates -> findNavController().navigate(R.id.calculateRatesFragment)
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
                is ProfileEvent.Logout -> goToStartScreen()
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

    private fun goToStartScreen() {
        findNavController().popBackStack(R.id.welcomeFragment, true)
        findNavController().navigate(R.id.welcomeFragment)
    }

    private fun showUserInfo(name: String, email: String, goals: String, rates: String) {
        val notificationPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.POST_NOTIFICATIONS
        )

        val notificationState = if (notificationPermission == PackageManager.PERMISSION_GRANTED) {
            getString(R.string.profile_notification_yes)
        } else {
            getString(R.string.profile_notification_no)
        }
        println("estado: $notificationState")

        with(binding) {
            tvSalutation.text = getString(R.string.profile_salutation, name)
            tdEmail.setText(getString(R.string.profile_email, email))
            tdNotifications.setText(getString(R.string.profile_notification))
            tdGoals.setText(getString(R.string.profile_goals, goals))
            tdRates.setText(getString(R.string.profile_rates, rates))
            tdNotifications.setText(
                getString(
                    R.string.profile_notification,
                    notificationState
                )
            )
        }
    }

    private fun showError() {
        this@ProfileFragment.view?.let {
            Snackbar.make(it, R.string.cannot_proceed_generic_error, Snackbar.LENGTH_LONG).show()
        }
    }
}
