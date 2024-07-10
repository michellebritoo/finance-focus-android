package br.com.michellebrito.financefocus.goal.details.data

import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.goal.details.domain.GoalDetailsRepository
import br.com.michellebrito.financefocus.goal.list.domain.GoalResponse
import retrofit2.Response

class GoalDetailsRepositoryImpl(
    private val client: GoalDetailsClient,
    private val preferenceStorage: PreferenceStorage
) : GoalDetailsRepository {
    override suspend fun getGoal(
        id: String,
        onSuccess: (Response<GoalResponse>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            val response = client.getGoal(
                authorization = preferenceStorage.getString(AUTHORIZATION),
                id = id
            )
            onSuccess(response)
        } catch (e: Exception) {
            onError(e)
        }
    }

    private companion object {
        const val AUTHORIZATION = "authorization"
    }
}
