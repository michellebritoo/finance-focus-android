package br.com.michellebrito.financefocus.goal.details.data

import br.com.michellebrito.financefocus.goal.list.domain.GoalResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header

interface GoalDetailsClient {

    @GET(GOAL)
    suspend fun getGoal(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Header(HEADER_ID) id: String
    ): Response<GoalResponse>

    @DELETE(GOAL_DELETE)
    suspend fun deleteGoal(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Header(HEADER_ID) id: String
    )

    private companion object {
        const val GOAL = "/goal"
        const val GOAL_DELETE = "/goal/delete"
        const val HEADER_AUTHORIZATION = "authorization"
        const val HEADER_ID = "id"
    }
}
