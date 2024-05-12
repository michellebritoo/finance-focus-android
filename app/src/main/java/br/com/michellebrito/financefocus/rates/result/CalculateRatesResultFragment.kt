package br.com.michellebrito.financefocus.rates.result

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentCalculateRatesResultBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class CalculateRatesResultFragment : Fragment(R.layout.fragment_calculate_rates_result) {
    private val binding: FragmentCalculateRatesResultBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        setupListeners()
        showMockResult()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
        bottomNavigation.selectedItemId = R.id.item_rates
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_goals -> findNavController().navigate(R.id.listGoalFragment)
                R.id.item_profile -> findNavController().navigate(R.id.profileFragment)
            }
            true
        }
    }

    private fun showMockResult() = with(binding) {
        binding.infoValue.setText(
            getString(R.string.rates_result_info_value, String.format("%.2f", 100f))
        )

        binding.infoRate.setText(
            getString(R.string.rates_result_info_rate, "10%")
        )

        binding.infoValueRate.setText(
            getString(R.string.rates_result_info_value_rate, String.format("%.2f", 110f))
        )

        showMockChartData()
    }

    private fun showMockChartData() = with(binding) {
        val yValueWithInterest = 1.10f

        val yValueWithoutInterest = 1.00f

        val entriesWithInterest = mutableListOf(
            Entry(0f, yValueWithoutInterest),
            Entry(1f, yValueWithoutInterest),
            Entry(2f, yValueWithoutInterest),
        )

        val entriesWithoutInterest = mutableListOf(
            Entry(0f, yValueWithoutInterest),
            Entry(1f, yValueWithInterest),
            Entry(2f, yValueWithInterest),
        )

        val dataSetWithInterest = LineDataSet(entriesWithInterest, "Com Juros")
        dataSetWithInterest.color = Color.RED
        dataSetWithInterest.setDrawValues(false)
        dataSetWithInterest.mode = LineDataSet.Mode.LINEAR

        val dataSetWithoutInterest = LineDataSet(entriesWithoutInterest, "Sem Juros")
        dataSetWithoutInterest.color = Color.BLUE
        dataSetWithoutInterest.setDrawValues(false)
        dataSetWithoutInterest.mode = LineDataSet.Mode.LINEAR

        val dataSets: MutableList<ILineDataSet> = ArrayList()
        dataSets.add(dataSetWithInterest)
        dataSets.add(dataSetWithoutInterest)

        val lineData = LineData(dataSets)

        chart.data = lineData
        chart.description.isEnabled = false
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.axisRight.isEnabled = false

        chart.axisLeft.axisMinimum = 0f
        chart.axisLeft.axisMaximum = 2f

        chart.invalidate()
    }
}