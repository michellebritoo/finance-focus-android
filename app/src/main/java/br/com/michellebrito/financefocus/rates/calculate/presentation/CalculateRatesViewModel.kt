package br.com.michellebrito.financefocus.rates.calculate.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.rates.calculate.domain.CalculateRatesRepository
import br.com.michellebrito.financefocus.rates.calculate.domain.RatesRequestModel
import kotlinx.coroutines.launch

class CalculateRatesViewModel(private val repository: CalculateRatesRepository): ViewModel() {
    private val _viewState = MutableLiveData<CalculateRatesEvent>()
    val viewState: LiveData<CalculateRatesEvent> = _viewState

    fun calculateRates(value: Double, rate: Double, time: Int, factor: Int, byMonth: Boolean) {
        val model = RatesRequestModel(value, rate, time, factor)
        sendRatesRequest(model, byMonth)
    }

    private fun sendRatesRequest(model: RatesRequestModel, byMonth: Boolean) {
        sendUIEvent(CalculateRatesEvent.ShowLoading)
        viewModelScope.launch {
            val response = if (byMonth) {
                repository.calculateRateByMonth(model)
            } else {
                repository.calculateRateByYear(model)
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    sendUIEvent(CalculateRatesEvent.GoToResultScreen(it))
                } ?: {
                    sendUIEvent(CalculateRatesEvent.ShowError)
                }
            } else {
                sendUIEvent(CalculateRatesEvent.ShowError)
            }
            sendUIEvent(CalculateRatesEvent.HideLoading)
        }
    }

    private fun sendUIEvent(event: CalculateRatesEvent) {
        _viewState.value = event
    }
}
