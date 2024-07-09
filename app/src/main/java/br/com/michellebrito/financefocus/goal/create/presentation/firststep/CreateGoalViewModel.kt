package br.com.michellebrito.financefocus.goal.create.presentation.firststep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.michellebrito.financefocus.common.presentation.Event
import br.com.michellebrito.financefocus.util.extensions.isEmptyOrBlank

class CreateGoalViewModel : ViewModel() {
    private val _viewState = MutableLiveData<Event<CreateGoalFirstStepEvent>>()
    val viewState: LiveData<Event<CreateGoalFirstStepEvent>> = _viewState

    fun onContinueButtonClicked(name: String, value: Float) {
        when {
            name.isEmptyOrBlank() -> {
                sendUIEvent(CreateGoalFirstStepEvent.ShowInputNameError)
            }
            (value <= 0) -> {
                sendUIEvent(CreateGoalFirstStepEvent.ShowInputValueError)
            }
            else -> sendUIEvent(CreateGoalFirstStepEvent.GoToNextStep)
        }
    }

    private fun sendUIEvent(event: CreateGoalFirstStepEvent) {
        _viewState.value = Event(event)
    }
}
