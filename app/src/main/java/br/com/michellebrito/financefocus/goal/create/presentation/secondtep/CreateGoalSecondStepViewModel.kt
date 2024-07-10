package br.com.michellebrito.financefocus.goal.create.presentation.secondtep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.goal.create.domain.CreateGoalRepository
import br.com.michellebrito.financefocus.goal.create.domain.CreateGoalRequest
import br.com.michellebrito.financefocus.util.extensions.isValidDate
import kotlinx.coroutines.launch

class CreateGoalSecondStepViewModel(private val repository: CreateGoalRepository) : ViewModel() {
    private val _viewState = MutableLiveData<CreateGoalSecondStepEvent>()
    val viewState: LiveData<CreateGoalSecondStepEvent> = _viewState

    private var name = ""
    private var description = ""
    private var value = 0f

    fun onStart(name: String, description: String, value: Float) {
        this.name = name
        this.description = description
        this.value = value
    }

    fun onContinueButtonClicked(
        gradualProgress: Boolean,
        monthFrequency: Boolean,
        initDate: String,
        finishDate: String
    ) {
        val model = CreateGoalRequest(
            name = name,
            description = description,
            totalValue = value,
            gradualProgress = gradualProgress,
            monthFrequency = monthFrequency,
            initDate = initDate,
            finishDate = finishDate
        )

        when {
            initDate.isValidDate().not() -> sendUIEvent(CreateGoalSecondStepEvent.InitDateError)
            finishDate.isValidDate().not() -> sendUIEvent(CreateGoalSecondStepEvent.FinishDateError)
            else -> sendCreateGoalRequest(model)
        }
    }

    private fun sendCreateGoalRequest(model: CreateGoalRequest) {
        sendUIEvent(CreateGoalSecondStepEvent.ShowLoading)
        viewModelScope.launch {
            repository.createGoal(
                model = model,
                onSuccess = {
                    sendUIEvent(CreateGoalSecondStepEvent.CreateWithSuccess)
                },
                onError = {
                    sendUIEvent(CreateGoalSecondStepEvent.ShowError)
                }
            )
            sendUIEvent(CreateGoalSecondStepEvent.HideLoading)
        }
    }

    private fun sendUIEvent(event: CreateGoalSecondStepEvent) {
        _viewState.value = event
    }
}
