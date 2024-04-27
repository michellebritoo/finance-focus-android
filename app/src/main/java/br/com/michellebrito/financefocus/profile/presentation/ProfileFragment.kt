package br.com.michellebrito.financefocus.profile.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentProfileBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    override fun onResume() {
        super.onResume()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }

        bottomNavigation.selectedItemId = R.id.item_profile
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_rates -> findNavController().navigate(R.id.goalDetailsFragment)
                R.id.item_goals -> findNavController().navigate(R.id.listGoalFragment)
            }
            true
        }
    }
}
