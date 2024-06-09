package br.com.michellebrito.financefocus.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.login.domain.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository): ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun onLoginPressed(email: String, password: String) {
        viewModelScope.launch {
            repository.authWithEmailAndPassword(email, password) { result ->
                _loginResult.value = result
            }
        }
    }
}
