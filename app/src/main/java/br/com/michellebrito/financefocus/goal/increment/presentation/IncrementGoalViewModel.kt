package br.com.michellebrito.financefocus.goal.increment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.goal.increment.domain.IncrementGoalRepository
import br.com.michellebrito.financefocus.goal.increment.domain.IncrementModelRequest
import br.com.michellebrito.financefocus.goal.increment.domain.ListIncrementItemModel
import kotlinx.coroutines.launch

class IncrementGoalViewModel(private val repository: IncrementGoalRepository) : ViewModel() {
    private val _viewState = MutableLiveData<IncrementGoalEvent>()
    val viewState: LiveData<IncrementGoalEvent> = _viewState

    private var goalId: String = ""

    fun onStart(id: String) {
        this.goalId = id
        getExpectedGoals()
    }

    fun incrementGoal(id: String, value: Float, completed: Boolean) {
        when {
            isValidValue(value).not() -> sendUIEvent(IncrementGoalEvent.InvalidValue)
            completed -> sendUIEvent(IncrementGoalEvent.HasCompletedDeposit)
            else -> sendIncrementGoalRequest(id, value)
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
                    val list = it.body()?.map { deposits ->
                        ListIncrementItemModel(
                            id = deposits.id,
                            value = deposits.value,
                            completed = deposits.completed
                        )
                    }
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
