package br.com.michellebrito.financefocus.rates.calculate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.rates.calculate.domain.CalculateRatesRepository
import br.com.michellebrito.financefocus.rates.calculate.domain.RatesRequestModel
import kotlinx.coroutines.launch

class CalculateRatesViewModel(private val repository: CalculateRatesRepository): ViewModel() {
    fun calculateRates(value: Double, rate: Double, time: Int, factor: Int, byMonth: Boolean) {
        val model = RatesRequestModel(value, rate, time, factor)
        sendRatesRequest(model, byMonth)
    }

    private fun sendRatesRequest(model: RatesRequestModel, byMonth: Boolean) {
        viewModelScope.launch {
            if (byMonth) {
                val response = repository.calculateRateByMonth(model)
                if (response.isSuccessful) println(response.body())
            } else {
                repository.calculateRateByYear(model)
            }
        }
    }
}
