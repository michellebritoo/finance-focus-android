package br.com.michellebrito.financefocus.goal.details.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentGoalDetailsBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class GoalDetailsFragment : Fragment(R.layout.fragment_goal_details) {
    private val binding: FragmentGoalDetailsBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        setupListeners()
        setupProgressChart(80f)
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun setupProgressChart(progress: Float) = binding.slimChart.apply {
        stats = floatArrayOf(progress)
        text = "${progress.toInt()}%"

        setStartAnimationDuration(3000)
        playStartAnimation()
    }
}