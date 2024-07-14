package br.com.michellebrito.financefocus.rates.calculate.domain

data class RatesRequestModel(
    val amount: Double,
    val rateValue: Double,
    val time: Int,
    val factor: Int
)