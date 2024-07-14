package br.com.michellebrito.financefocus.goal.list.domain

import retrofit2.Response

interface ListGoalRepository {
    suspend fun getGoals(
        onSuccess: (Response<List<GoalResponse>>) -> Unit,
        onError: (Throwable) -> Unit
    )
}
