package br.com.michellebrito.financefocus.goal.create.domain

interface CreateGoalRepository {
    suspend fun createGoal(
        model: CreateGoalRequest,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )
}
