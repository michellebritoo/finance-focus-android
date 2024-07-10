package br.com.michellebrito.financefocus.goal.increment.presentation

sealed class IncrementGoalEvent {
    data object ShowLoading: IncrementGoalEvent()
    data object HideLoading: IncrementGoalEvent()
    data object InvalidValue: IncrementGoalEvent()
    data object ShowError: IncrementGoalEvent()
    data object OnSuccess: IncrementGoalEvent()
}
