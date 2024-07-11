package br.com.michellebrito.financefocus.goal.increment.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentIncrementGoalBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject

class IncrementGoalFragment : Fragment(R.layout.fragment_increment_goal) {
    private val binding: FragmentIncrementGoalBinding by viewBinding()
    private val viewModel: IncrementGoalViewModel by inject()

    override fun onResume() {
        super.onResume()

        val args = arguments?.let { IncrementGoalFragmentArgs.fromBundle(it) }
        args?.let {
            viewModel.onStart(args.goaltoincrement)
        }
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
    }
}
