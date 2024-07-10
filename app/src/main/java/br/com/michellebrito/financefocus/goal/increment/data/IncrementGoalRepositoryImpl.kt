package br.com.michellebrito.financefocus.goal.increment.data

import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.goal.increment.domain.IncrementGoalRepository
import br.com.michellebrito.financefocus.goal.increment.domain.IncrementModelRequest

class IncrementGoalRepositoryImpl(
    private val client: IncrementGoalClient,
    private val preferenceStorage: PreferenceStorage
): IncrementGoalRepository {
    override suspend fun sendIncrementRequest(
        model: IncrementModelRequest,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            client.incrementGoal(
                authorization = preferenceStorage.getString(AUTHORIZATION),
                model = model
            )
            onSuccess()
        } catch (e: Exception) {
            onError(e)
        }
    }

    private companion object {
        const val AUTHORIZATION = "authorization"
    }
}