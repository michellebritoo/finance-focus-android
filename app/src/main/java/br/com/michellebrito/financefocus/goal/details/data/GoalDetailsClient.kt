package br.com.michellebrito.financefocus.goal.details.data

import br.com.michellebrito.financefocus.goal.list.domain.GoalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GoalDetailsClient {

    @GET(GOAL)
    suspend fun getGoal(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Header(HEADER_ID) id: String
    ): Response<GoalResponse>

    private companion object {
        const val GOAL = "/goal"
        const val HEADER_AUTHORIZATION = "authorization"
        const val HEADER_ID = "id"
    }
}
