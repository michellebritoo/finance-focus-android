package br.com.michellebrito.financefocus.welcome.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.welcome.domain.WelcomeRepository
import kotlinx.coroutines.launch

class WelcomeViewModel(private val repository: WelcomeRepository): ViewModel() {
    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean> get() = _isUserAuthenticated

    fun getAuthInfo() {
        viewModelScope.launch {
            _isUserAuthenticated.value = repository.isLoggedUser()
        }
    }
}