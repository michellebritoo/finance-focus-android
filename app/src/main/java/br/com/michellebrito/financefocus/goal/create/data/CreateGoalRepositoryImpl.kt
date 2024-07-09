package br.com.michellebrito.financefocus.goal.create.data

import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.goal.create.domain.CreateGoalRepository
import br.com.michellebrito.financefocus.goal.create.domain.CreateGoalRequest

class CreateGoalRepositoryImpl(
    private val client: CreateGoalClient,
    private val preferenceStorage: PreferenceStorage
) : CreateGoalRepository {

    override suspend fun createGoal(model: CreateGoalRequest) {
        client.createGoal(
            authorization = preferenceStorage.getString(AUTHORIZATION),
            model = model
        )
    }

    private companion object {
        const val AUTHORIZATION = "authorization"
    }
}
