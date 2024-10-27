package br.com.michellebrito.financefocus.goal.increment.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.MainActivity
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.common.presentation.FeedbackFragmentDirections
import br.com.michellebrito.financefocus.databinding.FragmentIncrementGoalBinding
import br.com.michellebrito.financefocus.goal.increment.domain.ListIncrementItemModel
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
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
        observeEvents()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
        floatingActionButton.setOnClickListener {
            val bottomSheet = NumberInputBottomSheet { value ->
                viewModel.incrementDifferentValueGoal(value.toFloat())
            }
            bottomSheet.show(childFragmentManager, NumberInputBottomSheet::class.java.name)
        }
    }

    private fun observeEvents() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is IncrementGoalEvent.ShowLoading -> showLoading()
                is IncrementGoalEvent.HideLoading -> hideLoading()
                is IncrementGoalEvent.ShowExpectedDeposits -> showList(state.list)
                is IncrementGoalEvent.ShowError -> showError()
                is IncrementGoalEvent.InvalidValue -> showError()
                is IncrementGoalEvent.HasCompletedDeposit -> showError()
                is IncrementGoalEvent.OnIncrementWithSuccess -> showSuccessIncrementFeedback()
            }
        }
    }

    private fun showLoading() = (requireActivity() as MainActivity).showLoading()

    private fun hideLoading() = (requireActivity() as MainActivity).hideLoading()

    private fun showError() {
        this@IncrementGoalFragment.view?.let {
            Snackbar.make(it, R.string.cannot_proceed_generic_error, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showList(list: List<ListIncrementItemModel>) = with(binding) {
        rvIncrementList.adapter = ListIncrementAdapter(list).apply {
            onItemClick = {
                viewModel.incrementGoal(it.id, it.value, it.completed)
            }
        }
    }

    private fun showSuccessIncrementFeedback() {
        val action = FeedbackFragmentDirections.actionGlobalFeedbackFragment(
            title = getString(R.string.goal_increment_success_feedback_title),
            description = getString(R.string.goal_increment_success_feedback_description),
            buttonText = getString(R.string.btn_close),
            onCloseAction = R.id.listGoalFragment
        )
        findNavController().navigate(action)
    }
}
