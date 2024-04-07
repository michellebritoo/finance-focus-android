package br.com.michellebrito.financefocus.goal.create.createfirststep.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentCreateGoalBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class CreateGoalFragment : Fragment(R.layout.fragment_create_goal) {
    private val binding: FragmentCreateGoalBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
