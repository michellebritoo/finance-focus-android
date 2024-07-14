package br.com.michellebrito.financefocus.goal.list.data

import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.goal.list.domain.GoalResponse
import br.com.michellebrito.financefocus.goal.list.domain.ListGoalRepository
import retrofit2.Response

class ListGoalRepositoryImpl(
    private val client: ListGoalClient,
    private val preferenceStorage: PreferenceStorage
) : ListGoalRepository {

    override suspend fun getGoals(
        onSuccess: (Response<List<GoalResponse>>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            val response = client.getGoalsList(
                preferenceStorage.getString(AUTHORIZATION)
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
