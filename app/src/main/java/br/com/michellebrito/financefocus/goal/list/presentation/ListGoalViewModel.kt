package br.com.michellebrito.financefocus.goal.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.goal.list.domain.ListGoalRepository
import br.com.michellebrito.financefocus.goal.list.model.ListGoalItemModel
import kotlinx.coroutines.launch

class ListGoalViewModel(private val repository: ListGoalRepository): ViewModel() {
    private val _viewState = MutableLiveData<ListGoalEvent>()
    val viewState: LiveData<ListGoalEvent> = _viewState

    fun getGoalsList() {
        viewModelScope.launch {
            sendUIEvent(ListGoalEvent.ShowLoading)
            repository.getGoals(
                onSuccess = { response ->
                    val list = response.body()?.map { goal ->
                        ListGoalItemModel(
                            title = goal.name,
                            date = goal.finishDate,
                            id = goal.id
                        )
                    }
                   if (list != null && list != emptyList<ListGoalItemModel>()) {
                       sendUIEvent(ListGoalEvent.ShowList(list))
                   } else {
                       sendUIEvent(ListGoalEvent.ShowEmptyState)
                   }
                },
                onError = {
                    sendUIEvent(ListGoalEvent.ShowError)
                }
            )
            sendUIEvent(ListGoalEvent.HideLoading)
        }
    }

    private fun sendUIEvent(event: ListGoalEvent) {
        _viewState.value = event
    }
}
