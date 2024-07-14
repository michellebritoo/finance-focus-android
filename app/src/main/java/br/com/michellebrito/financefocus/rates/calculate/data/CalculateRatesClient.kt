package br.com.michellebrito.financefocus.rates.calculate.data

import br.com.michellebrito.financefocus.rates.calculate.domain.CalculateRatesResponse
import br.com.michellebrito.financefocus.rates.calculate.domain.RatesRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CalculateRatesClient {

    @POST(CALCULATE_BY_MONTH)
    suspend fun calculateRatesByMonth(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Body body: RatesRequestModel
    ): Response<CalculateRatesResponse>

    @POST(CALCULATE_BY_YEAR)
    suspend fun calculateRatesByYear(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Body body: RatesRequestModel
    ): Response<CalculateRatesResponse>

    private companion object {
        const val CALCULATE_BY_MONTH = "/rates/calculate/month"
        const val CALCULATE_BY_YEAR = "/rates/calculate/year"
        const val HEADER_AUTHORIZATION = "authorization"
    }
}
