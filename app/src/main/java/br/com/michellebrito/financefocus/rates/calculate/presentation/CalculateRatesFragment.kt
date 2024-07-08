package br.com.michellebrito.financefocus.rates.calculate.presentation

import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.MainActivity
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentCalculateRatesBinding
import br.com.michellebrito.financefocus.rates.calculate.domain.CalculateRatesResponse
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.koin.android.ext.android.inject

class CalculateRatesFragment : Fragment(R.layout.fragment_calculate_rates) {
    private val binding: FragmentCalculateRatesBinding by viewBinding()
    private val viewModel: CalculateRatesViewModel by inject()

    override fun onResume() {
        super.onResume()
        setupListeners()
        setupObservers()
        setupOptions()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
        btnCalculate.setOnClickListener { calculateRatesButtonClicked() }
        bottomNavigation.selectedItemId = R.id.item_rates
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_goals -> findNavController().navigate(R.id.listGoalFragment)
                R.id.item_profile -> findNavController().navigate(R.id.profileFragment)
            }
            true
        }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is CalculateRatesEvent.ShowLoading -> showLoading()
                is CalculateRatesEvent.HideLoading -> hideLoading()
                is CalculateRatesEvent.GoToResultScreen -> goToResult(state.model)
                is CalculateRatesEvent.ShowError -> showError()
            }
        }
    }

    private fun showLoading() = (requireActivity() as MainActivity).showLoading()

    private fun hideLoading() = (requireActivity() as MainActivity).hideLoading()

    private fun showError() {
        this@CalculateRatesFragment.view?.let {
            Snackbar.make(it, R.string.signup_confirm_failure_generic, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun calculateRatesButtonClicked() = with(binding) {
        val formattedValueWithoutMask = binding.etValue.text?.toString()?.replace("[^\\d,]".toRegex(), "")?.replace(",", ".")
        val formattedRateWithoutMask = binding.etRate.text?.toString()?.replace("[^\\d,]".toRegex(), "")?.replace(",", ".")
        val formattedPeriodWithoutMask = binding.etPeriod.text?.toString()?.replace("[^\\d]".toRegex(), "")
        val byMonth = binding.btnFrequencyMonth.isSelected
        viewModel.calculateRates(
            (formattedValueWithoutMask?.toDouble()) ?: 0.0,
            (formattedRateWithoutMask?.toDouble()) ?: 0.0,
            (formattedPeriodWithoutMask?.toInt()) ?: 1,
            binding.spinnerOptions.selectedItemId.toInt().inc(),
            byMonth
        )
    }

    private fun setupOptions() {
        binding.btnFrequencyMonth.isSelected = true
        val items = listOf(
            getString(R.string.calculate_rates_vehicle_option),
            getString(R.string.calculate_rates_house_option),
            getString(R.string.calculate_rates_other_option),
            getString(R.string.calculate_rates_credit_option),
        )

        val adapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            items
        )

        binding.spinnerOptions.adapter = adapter
    }

    private fun goToResult(model: CalculateRatesResponse) {
        val json = Gson().toJson(model)
        val action = CalculateRatesFragmentDirections.calculateRatesToCalculateRatesResult(
            json,
            binding.btnFrequencyMonth.isSelected
        )
        findNavController().navigate(action)
    }
}
