package br.com.michellebrito.financefocus.home.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.MainActivity
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentHomeBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by inject()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkAndRequestNotificationPermission()
    }

    override fun onResume() {
        super.onResume()
        setupListeners()
        getDeviceToken()
    }

    private fun setupListeners() = with(binding) {
        btnCreateGoal.setOnClickListener { findNavController().navigate(R.id.homeToCreateGoal) }
        btnCheckRates.setOnClickListener { findNavController().navigate(R.id.homeToCalculateRates) }
        btnViewGoals.setOnClickListener { findNavController().navigate(R.id.listGoalFragment) }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                minimizeApp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun minimizeApp() {
        requireActivity().moveTaskToBack(true)
    }

    private fun getDeviceToken() {
        (requireActivity() as MainActivity).getDeviceToken {
            viewModel.refreshDeviceToken(it)
        }
    }

    private fun checkAndRequestNotificationPermission() {
        val permission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.POST_NOTIFICATIONS
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}
