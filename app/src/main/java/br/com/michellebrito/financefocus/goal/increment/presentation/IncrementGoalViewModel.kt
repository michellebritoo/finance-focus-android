package br.com.michellebrito.financefocus.goal.increment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.goal.increment.domain.ExpectedDepositResponse
import br.com.michellebrito.financefocus.goal.increment.domain.IncrementGoalRepository
import br.com.michellebrito.financefocus.goal.increment.domain.IncrementModelRequest
import br.com.michellebrito.financefocus.goal.increment.domain.ListIncrementItemModel
import kotlinx.coroutines.launch

class IncrementGoalViewModel(private val repository: IncrementGoalRepository) : ViewModel() {
    private val _viewState = MutableLiveData<IncrementGoalEvent>()
    val viewState: LiveData<IncrementGoalEvent> = _viewState

    private var goalCompleted = false
    private var goalId: String = ""
    private var depositList = listOf<ExpectedDepositResponse>()

    fun onStart(id: String, completed: Boolean) {
        this.goalCompleted = completed
        this.goalId = id
        getExpectedGoals()

        if (completed) sendUIEvent(IncrementGoalEvent.HasCompletedGoal)
    }

    fun incrementGoal(id: String, value: Float, completed: Boolean) {
        when {
            isValidValue(value).not() -> sendUIEvent(IncrementGoalEvent.InvalidValue)
            completed -> sendUIEvent(IncrementGoalEvent.HasCompletedDeposit)
            else -> sendIncrementGoalRequest(id, value)
        }
    }

    fun incrementDifferentValueGoal(value: Float) {
        if (isValidValue(value).not()) {
            sendUIEvent(IncrementGoalEvent.InvalidValue)
        } else {
            sendIncrementGoalRequest(
                id = depositList.filter { it.completed.not() }.firstOrNull()?.id.orEmpty(),
                value = value
            )
        }
    }

    private fun isValidValue(value: Float): Boolean {
        return value > 0f
    }

    private fun getExpectedGoals() {
        viewModelScope.launch {
            sendUIEvent(IncrementGoalEvent.ShowLoading)
            repository.getExpectedDeposits(
                id = goalId,
                onSuccess = {
                    depositList = it.body() ?: listOf()
                    val list = it.body()?.map { deposits ->
                        ListIncrementItemModel(
                            id = deposits.id,
                            value = deposits.value,
                            completed = deposits.completed
                        )
                    }?.sortedBy { it.value }
                    list?.let { sendUIEvent(IncrementGoalEvent.ShowExpectedDeposits(list)) }
                },
                onError = {
                    sendUIEvent(IncrementGoalEvent.ShowError)
                }
            )
            sendUIEvent(IncrementGoalEvent.HideLoading)
        }
    }

    private fun sendIncrementGoalRequest(id: String, value: Float) {
        viewModelScope.launch {
            sendUIEvent(IncrementGoalEvent.ShowLoading)
            repository.sendIncrementRequest(
                model = IncrementModelRequest(goalId, id, value),
                onSuccess = {
                    sendUIEvent(IncrementGoalEvent.OnIncrementWithSuccess)
                },
                onError = {
                    sendUIEvent(IncrementGoalEvent.ShowError)
                }
            )
            sendUIEvent(IncrementGoalEvent.HideLoading)
        }
    }

    private fun sendUIEvent(event: IncrementGoalEvent) {
        _viewState.value = event
    }
}
