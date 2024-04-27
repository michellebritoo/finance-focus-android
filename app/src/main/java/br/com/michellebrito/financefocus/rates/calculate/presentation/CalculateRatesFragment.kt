package br.com.michellebrito.financefocus.rates.calculate.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentCalculateRatesBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class CalculateRatesFragment : Fragment(R.layout.fragment_calculate_rates) {
    private val binding: FragmentCalculateRatesBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
        bottomNavigation.selectedItemId = R.id.item_rates
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_goals -> findNavController().navigate(R.id.listGoalFragment)
                R.id.profileFragment -> findNavController().navigate(R.id.profileFragment)
            }
            true
        }
    }
}

