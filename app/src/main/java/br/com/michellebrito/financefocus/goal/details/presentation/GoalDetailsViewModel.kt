package br.com.michellebrito.financefocus.goal.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.goal.details.domain.GoalDetailsRepository
import kotlinx.coroutines.launch

class GoalDetailsViewModel(private val repository: GoalDetailsRepository) : ViewModel() {
    private val _viewState = MutableLiveData<GoalDetailEvent>()
    val viewState: LiveData<GoalDetailEvent> = _viewState
    private var goalId: String = ""
    private var completed = false

    fun getGoalRequest(id: String) {
        goalId = id
        sendUIEvent(GoalDetailEvent.ShowLoading)
        viewModelScope.launch {
            repository.getGoal(
                id = id,
                onSuccess = { response ->
                    if (response.isSuccessful) {
                        val goal = response.body()
                        completed = goal?.concluded ?: false
                        goal?.run {
                            sendUIEvent(
                                GoalDetailEvent.ShowGoal(
                                    name = name,
                                    description = description,
                                    value = totalValue,
                                    remainingValue = remainingValue,
                                    progress = getProgress(totalValue, remainingValue),
                                    date = "$initDate - $finishDate",
                                    gradualProgress = gradualProgress,
                                    monthFrequency = monthFrequency
                                )
                            )
                        }
                    } else {
                        sendUIEvent(GoalDetailEvent.ShowError)
                    }
                },
                onError = {
                    sendUIEvent(GoalDetailEvent.ShowError)
                }
            )
            sendUIEvent(GoalDetailEvent.HideLoading)
            if (completed) sendUIEvent(GoalDetailEvent.HasCompletedGoal)
        }
    }

    fun onIncrementClicked() {
        sendUIEvent(GoalDetailEvent.GoToIncrementScreen(goalId, completed))
    }

    fun onDeleteGoal() {
        sendUIEvent(GoalDetailEvent.ShowLoading)
        viewModelScope.launch {
            repository.deleteGoal(
                id = goalId,
                onSuccess = { sendUIEvent(GoalDetailEvent.OnSuccessDelete) },
                onError = { sendUIEvent(GoalDetailEvent.ShowError) }
            )
            sendUIEvent(GoalDetailEvent.HideLoading)
        }
    }

    private fun getProgress(totalValue: Double, remainingValue: Double): Float {
        return ((1 - (remainingValue / totalValue)) * 100).toFloat()
    }

    private fun sendUIEvent(event: GoalDetailEvent) {
        _viewState.value = event
    }
}
