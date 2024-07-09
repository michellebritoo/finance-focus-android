package br.com.michellebrito.financefocus.rates.result.presentation

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentCalculateRatesResultBinding
import br.com.michellebrito.financefocus.rates.calculate.domain.CalculateRatesResponse
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.gson.Gson

class CalculateRatesResultFragment : Fragment(R.layout.fragment_calculate_rates_result) {
    private val binding: FragmentCalculateRatesResultBinding by viewBinding()
    private lateinit var responseModel: CalculateRatesResponse
    private var byMonthRate = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.let {
            CalculateRatesResultFragmentArgs.fromBundle(it)
        }
        responseModel = Gson().fromJson(
            args?.calculateRatesResponse,
            CalculateRatesResponse::class.java
        )
        byMonthRate = args?.byMonth ?: true
    }

    override fun onResume() {
        super.onResume()
        setupListeners()
        showResult()
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
        binding.infoGlobalRate.setIconInfoClickListener { showDialog() }
    }

    private fun showDialog() {
        val message = StringBuilder()
        responseModel.lastRates.forEach {
            message.append(
                "${it.date} - ${it.value} %\n"
            )
        }
        AlertDialog.Builder(requireContext())
            .setTitle("Últimos registros do Banco Central do Brasil")
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    private fun showResult() = with(binding) {
        infoValue.setText(getString(R.string.rates_result_info_value, responseModel.amount))
        infoRate.setText(getString(R.string.rates_result_info_rate, formatByRate(responseModel.rateValue)))
        infoValueRate.setText(getString(R.string.rates_result_info_value_rate, responseModel.totalValueWithRate))
        infoPartValue.setText(getString(R.string.rates_result_info_part_rate, responseModel.partValue))
        infoGlobalRate.setText(responseModel.status)

        showChartData(
            responseModel.amount.replace("[^\\d,]".toRegex(), "").replace(",", ".").toFloat(),
            responseModel.totalValueWithRate.replace("[^\\d,]".toRegex(), "").replace(",", ".").toFloat()
        )
    }

    private fun showChartData(value: Float, totalValue: Float) = with(binding) {
        val yValueWithInterest = totalValue/1000

        val yValueWithoutInterest = value/1000

        val entriesWithInterest = mutableListOf(
            Entry(0f, yValueWithoutInterest),
            Entry(1f, yValueWithInterest),
            Entry(2f, yValueWithInterest),
        )

        val entriesWithoutInterest = mutableListOf(
            Entry(0f, yValueWithoutInterest),
            Entry(1f, yValueWithoutInterest),
            Entry(2f, yValueWithoutInterest),
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
        chart.axisLeft.axisMaximum = yValueWithInterest + 1f

        chart.invalidate()
    }

    private fun formatByRate(value: String): String {
        return if (byMonthRate) {
            value.plus(" a.m")
        } else {
            value.plus(" a.a")
        }
    }
}
