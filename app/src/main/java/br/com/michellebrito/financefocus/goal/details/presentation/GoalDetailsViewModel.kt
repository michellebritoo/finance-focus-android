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

    fun getGoalRequest(id: String) {
        sendUIEvent(GoalDetailEvent.ShowLoading)
        viewModelScope.launch {
            repository.getGoal(
                id = id,
                onSuccess = { response ->
                    if (response.isSuccessful) {
                        val goal = response.body()
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
        }
    }

    private fun getProgress(totalValue: Double, remainingValue: Double): Float {
        return ((1 - (remainingValue / totalValue)) * 100).toFloat()
    }

    private fun sendUIEvent(event: GoalDetailEvent) {
        _viewState.value = event
    }
}
