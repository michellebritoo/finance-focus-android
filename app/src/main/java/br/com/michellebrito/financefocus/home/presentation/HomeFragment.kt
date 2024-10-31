package br.com.michellebrito.financefocus.home.presentation

import androidx.activity.OnBackPressedCallback
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
}
