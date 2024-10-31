package br.com.michellebrito.financefocus.goal.list.presentation

import br.com.michellebrito.financefocus.goal.list.model.ListGoalItemModel

sealed class ListGoalEvent {
    data object ShowLoading: ListGoalEvent()
    data object HideLoading: ListGoalEvent()
    data object ShowError: ListGoalEvent()
    data object ShowEmptyState: ListGoalEvent()
    data class ShowList(val list: List<ListGoalItemModel>): ListGoalEvent()
}
