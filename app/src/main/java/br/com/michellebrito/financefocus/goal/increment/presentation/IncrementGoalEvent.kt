package br.com.michellebrito.financefocus.goal.increment.presentation

import br.com.michellebrito.financefocus.goal.increment.domain.ExpectedDepositResponse

sealed class IncrementGoalEvent {
    data object ShowLoading: IncrementGoalEvent()
    data object HideLoading: IncrementGoalEvent()
    data object InvalidValue: IncrementGoalEvent()
    data object ShowError: IncrementGoalEvent()
    data object OnIncrementWithSuccess: IncrementGoalEvent()
    data class ShowExpectedDeposits(val list: List<ExpectedDepositResponse>): IncrementGoalEvent()
}
