package br.com.michellebrito.financefocus.goal.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.goal.list.domain.ListGoalRepository
import kotlinx.coroutines.launch

class ListGoalViewModel(private val repository: ListGoalRepository): ViewModel() {

    fun getGoalsList() {
        viewModelScope.launch {
            repository.getGoals(
                onSuccess = {

                },
                onError = {

                }
            )
        }
    }
}
