package br.com.michellebrito.financefocus.goal.list.presentation

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.MainActivity
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentListGoalBinding
import br.com.michellebrito.financefocus.goal.list.model.ListGoalItemModel
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class ListGoalFragment : Fragment(R.layout.fragment_list_goal) {
    private val binding: FragmentListGoalBinding by viewBinding()
    private val viewModel: ListGoalViewModel by inject()

    override fun onResume() {
        super.onResume()
        setupListeners()
        observeEvents()
        viewModel.getGoalsList()
    }

    private fun setupListeners() = with(binding) {
        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().finish()
        }

        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.listGoalFragmentToCreateGoalFragment)
        }

        bottomNavigation.selectedItemId = R.id.item_goals
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_rates -> findNavController().navigate(R.id.listGoalToCalculateRates)
                R.id.item_profile -> findNavController().navigate(R.id.profileFragment)
            }
            true
        }
    }

    private fun observeEvents() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ListGoalEvent.ShowLoading -> showLoading()
                is ListGoalEvent.HideLoading -> hideLoading()
                is ListGoalEvent.ShowList -> showList(state.list)
                is ListGoalEvent.ShowError -> showError()
            }
        }
    }

    private fun showLoading() = (requireActivity() as MainActivity).showLoading()

    private fun hideLoading() = (requireActivity() as MainActivity).hideLoading()

    private fun showError() {
        this@ListGoalFragment.view?.let {
            Snackbar.make(it, R.string.cannot_proceed_generic_error, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showList(list: List<ListGoalItemModel>) = with(binding) {
        rvGoalList.adapter = ListGoalAdapter(list).apply {
            onItemClick = {
                findNavController().navigate(R.id.listGoalFragmentToGoalDetailsFragment)
            }
        }
    }
}
