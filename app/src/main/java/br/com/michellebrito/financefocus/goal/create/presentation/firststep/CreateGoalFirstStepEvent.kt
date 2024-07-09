package br.com.michellebrito.financefocus.goal.create.presentation.firststep

sealed class CreateGoalFirstStepEvent {
    data object ShowInputNameError: CreateGoalFirstStepEvent()
    data object ShowInputValueError: CreateGoalFirstStepEvent()
    data object GoToNextStep: CreateGoalFirstStepEvent()
}
