package br.com.michellebrito.financefocus.goal.increment.domain

import retrofit2.Response

interface IncrementGoalRepository {
    suspend fun getExpectedDeposits(
        id: String,
        onSuccess: (Response<List<ExpectedDepositResponse>>) -> Unit,
        onError: (Throwable) -> Unit
    )
    suspend fun sendIncrementRequest(
        model: IncrementModelRequest,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )
}
