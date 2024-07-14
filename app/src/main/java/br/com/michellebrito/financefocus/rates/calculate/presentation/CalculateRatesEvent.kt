package br.com.michellebrito.financefocus.rates.calculate.presentation

import br.com.michellebrito.financefocus.rates.calculate.domain.CalculateRatesResponse

sealed class CalculateRatesEvent {
    data object ShowLoading: CalculateRatesEvent()
    data object HideLoading: CalculateRatesEvent()
    data object ShowError: CalculateRatesEvent()
    data class GoToResultScreen(val model: CalculateRatesResponse): CalculateRatesEvent()
}
