package br.com.michellebrito.financefocus.rates.calculate.domain

data class CalculateRatesResponse(
    val amount: String,
    val rateValue: String,
    val status: String,
    val lastRates: List<RateResponseModel>,
    val partValue: String,
    val totalValueWithRate: String,
    val totalRate: String
)

data class RateResponseModel(
    val date: String,
    val value: Double
)
