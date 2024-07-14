package br.com.michellebrito.financefocus.goal.list.data

import br.com.michellebrito.financefocus.goal.list.domain.GoalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ListGoalClient {

    @GET(GOALS_LIST)
    suspend fun getGoalsList(
        @Header(HEADER_AUTHORIZATION) authorization: String,
    ): Response<List<GoalResponse>>

    private companion object {
        const val GOALS_LIST = "/goal/list"
        const val HEADER_AUTHORIZATION = "authorization"
    }
}
