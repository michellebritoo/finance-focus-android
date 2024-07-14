package br.com.michellebrito.financefocus.rates.calculate.data

import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.rates.calculate.domain.CalculateRatesRepository
import br.com.michellebrito.financefocus.rates.calculate.domain.CalculateRatesResponse
import br.com.michellebrito.financefocus.rates.calculate.domain.RatesRequestModel
import retrofit2.Response

class CalculateRatesRepositoryImpl(
    private val preferenceStorage: PreferenceStorage,
    private val client: CalculateRatesClient
) : CalculateRatesRepository {
    override suspend fun calculateRateByMonth(model: RatesRequestModel): Response<CalculateRatesResponse> {
        return client.calculateRatesByMonth(preferenceStorage.getString(AUTHORIZATION), model)
    }

    override suspend fun calculateRateByYear(model: RatesRequestModel): Response<CalculateRatesResponse> {
        return client.calculateRatesByYear(preferenceStorage.getString(AUTHORIZATION), model)
    }

    private companion object {
        const val AUTHORIZATION = "authorization"
    }
}
