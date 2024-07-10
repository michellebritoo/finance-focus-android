package br.com.michellebrito.financefocus.goal.details.domain

import br.com.michellebrito.financefocus.goal.list.domain.GoalResponse
import retrofit2.Response

interface GoalDetailsRepository {
    suspend fun getGoal(
        id: String,
        onSuccess: (Response<GoalResponse>) -> Unit,
        onError: (Throwable) -> Unit
    )
}
