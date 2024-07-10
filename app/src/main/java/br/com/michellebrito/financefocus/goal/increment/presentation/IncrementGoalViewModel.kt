package br.com.michellebrito.financefocus.goal.increment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.goal.increment.domain.IncrementGoalRepository
import br.com.michellebrito.financefocus.goal.increment.domain.IncrementModelRequest
import kotlinx.coroutines.launch

class IncrementGoalViewModel(private val repository: IncrementGoalRepository) : ViewModel() {
    private val _viewState = MutableLiveData<IncrementGoalEvent>()
    val viewState: LiveData<IncrementGoalEvent> = _viewState

    fun incrementGoal(id: String, value: Float) {
        if (isValidValue(value).not()) {
            sendUIEvent(IncrementGoalEvent.InvalidValue)
        } else {
            sendIncrementGoalRequest(id, value)
        }
    }

    private fun isValidValue(value: Float): Boolean {
        return value > 0f
    }

    private fun sendIncrementGoalRequest(id: String, value: Float) {
        viewModelScope.launch {
            sendUIEvent(IncrementGoalEvent.ShowLoading)
            repository.sendIncrementRequest(
                model = IncrementModelRequest(id, value),
                onSuccess = {
                    sendUIEvent(IncrementGoalEvent.OnSuccess)
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
