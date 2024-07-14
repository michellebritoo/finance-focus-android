package br.com.michellebrito.financefocus.rates.calculate.domain

import retrofit2.Response

interface CalculateRatesRepository {
    suspend fun calculateRateByMonth(model: RatesRequestModel): Response<CalculateRatesResponse>
    suspend fun calculateRateByYear(model: RatesRequestModel): Response<CalculateRatesResponse>
}
