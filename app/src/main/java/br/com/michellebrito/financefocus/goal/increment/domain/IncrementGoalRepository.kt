package br.com.michellebrito.financefocus.goal.increment.domain

interface IncrementGoalRepository {
    suspend fun sendIncrementRequest(
        model: IncrementModelRequest,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )
}
