package br.com.michellebrito.financefocus.goal.details.presentation

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.MainActivity
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentGoalDetailsBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class GoalDetailsFragment : Fragment(R.layout.fragment_goal_details) {
    private val binding: FragmentGoalDetailsBinding by viewBinding()
    private val viewModel: GoalDetailsViewModel by inject()

    override fun onResume() {
        super.onResume()
        setupListeners()
        observeEvents()
        val args = arguments?.let {
            GoalDetailsFragmentArgs.fromBundle(it)
        }
        args?.let {
            viewModel.getGoalRequest(args.idgoal)
        }
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
        btnDeleteGoal.setOnClickListener { onDeleteGoalClicked() }
        bottomNavigation.selectedItemId = R.id.item_goals
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_rates -> findNavController().navigate(R.id.goalDetailsToCalculateRates)
                R.id.item_goals -> findNavController().navigate(R.id.listGoalFragment)
                R.id.item_profile -> findNavController().navigate(R.id.profileFragment)
            }
            true
        }
    }

    private fun onDeleteGoalClicked() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.goal_details_delete_dialog_title))
            .setMessage(getString(R.string.goal_details_delete_dialog_message))
            .setPositiveButton(android.R.string.ok) { _, _ ->
                viewModel.onDeleteGoal()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun observeEvents() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is GoalDetailEvent.ShowLoading -> showLoading()
                is GoalDetailEvent.HideLoading -> hideLoading()
                is GoalDetailEvent.ShowError -> showError()
                is GoalDetailEvent.OnSuccessDelete -> onDeleteWithSuccess()
                is GoalDetailEvent.ShowGoal -> showGoal(
                    state.name,
                    state.description,
                    state.value,
                    state.remainingValue,
                    state.progress,
                    state.date,
                    state.gradualProgress,
                    state.monthFrequency
                )
            }
        }
    }

    private fun onDeleteWithSuccess() {

    }

    private fun showLoading() = (requireActivity() as MainActivity).showLoading()

    private fun hideLoading() = (requireActivity() as MainActivity).hideLoading()

    private fun showError() {
        this@GoalDetailsFragment.view?.let {
            Snackbar.make(it, R.string.cannot_proceed_generic_error, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showGoal(
        name: String,
        description: String,
        value: Double,
        remainingValue: Double,
        progress: Float,
        date: String,
        gradualProgress: Boolean,
        monthFrequency: Boolean
    ) = with(binding) {
        tvCardTitle.text = name
        tvCardDescription.text = description
        tvCardValue.text = getString(R.string.goal_details_total_value, String.format("%.2f", value))
        tvCardRemainingValue.text = getString(R.string.goal_details_remaining_value, String.format("%.2f", remainingValue))
        tvCardDate.text = date
        tvCardFrequency.text = if (monthFrequency) {
            getString(R.string.goal_details_month_frequency)
        } else {
            getString(R.string.goal_details_week_frequency)
        }
        tvCardProgressType.text = if (gradualProgress) {
            getString(R.string.goal_details_relative_value)
        } else {
            getString(R.string.goal_details_fixed_value)
        }

        showProgressChart(progress)
    }

    private fun showProgressChart(progress: Float) = binding.slimChart.apply {
        stats = floatArrayOf(progress)
        text = "${progress.toInt()}%"

        setStartAnimationDuration(3000)
        playStartAnimation()
    }
}
