package br.com.michellebrito.financefocus.goal.create.presentation.secondtep

sealed class CreateGoalSecondStepEvent {
    data object ShowLoading: CreateGoalSecondStepEvent()
    data object HideLoading: CreateGoalSecondStepEvent()
    data object InitDateError: CreateGoalSecondStepEvent()
    data object FinishDateError: CreateGoalSecondStepEvent()
    data object ShowError: CreateGoalSecondStepEvent()
    data object GoToList: CreateGoalSecondStepEvent()
}
