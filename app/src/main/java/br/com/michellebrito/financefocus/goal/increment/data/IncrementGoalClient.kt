package br.com.michellebrito.financefocus.goal.increment.data

import br.com.michellebrito.financefocus.goal.increment.domain.ExpectedDepositResponse
import br.com.michellebrito.financefocus.goal.increment.domain.IncrementModelRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface IncrementGoalClient {
    @GET(PRE_INCREMENT)
    suspend fun getExpectedDeposits(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Header(HEADER_ID) id: String
    ): Response<List<ExpectedDepositResponse>>

    @POST(GOAL_INCREMENT)
    suspend fun incrementGoal(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Body model: IncrementModelRequest
    )

    private companion object {
        const val PRE_INCREMENT = "/pre/increment"
        const val GOAL_INCREMENT = "/goal/increment"
        const val HEADER_AUTHORIZATION = "authorization"
        const val HEADER_ID = "id"
    }
}
