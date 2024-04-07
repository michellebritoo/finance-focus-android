package br.com.michellebrito.financefocus.home.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentHomeBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        btnCreateGoal.setOnClickListener {
            findNavController().navigate(R.id.homeToCreateGoal)
        }
    }
}
