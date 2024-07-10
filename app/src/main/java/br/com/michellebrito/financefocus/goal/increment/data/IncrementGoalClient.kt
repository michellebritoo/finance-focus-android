package br.com.michellebrito.financefocus.goal.increment.data

import br.com.michellebrito.financefocus.goal.increment.domain.IncrementModelRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface IncrementGoalClient {
    @POST(GOAL_INCREMENT)
    suspend fun incrementGoal(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Body model: IncrementModelRequest
    )

    private companion object {
        const val GOAL_INCREMENT = "/goal/increment"
        const val HEADER_AUTHORIZATION = "authorization"
    }
}
