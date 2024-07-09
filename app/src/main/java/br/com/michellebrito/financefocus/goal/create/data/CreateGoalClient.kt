package br.com.michellebrito.financefocus.goal.create.data

import br.com.michellebrito.financefocus.goal.create.domain.CreateGoalRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CreateGoalClient {

    @POST(CREATE_GOAL)
    suspend fun createGoal(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Body model: CreateGoalRequest
    )

    private companion object {
        const val CREATE_GOAL = "/goal/create"
        const val HEADER_AUTHORIZATION = "authorization"
    }
}
