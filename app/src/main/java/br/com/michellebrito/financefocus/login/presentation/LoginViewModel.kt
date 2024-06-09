package br.com.michellebrito.financefocus.login.presentation

import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.login.domain.LoginRepository
import br.com.michellebrito.financefocus.util.extensions.isEmptyOrBlank
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _viewState = MutableLiveData<LoginEvent>()
    val viewState: LiveData<LoginEvent> get() = _viewState

    fun onLoginPressed(email: String, password: String) {
        when {
            isEmailValid(email).not() -> sendUIEvent(LoginEvent.EmailInvalid)
            isPasswordValid(password).not() -> sendUIEvent(LoginEvent.PasswordInvalid)
            else -> authWithEmailAndPassword(email.trim(), password.trim())
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email.trim()).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isEmptyOrBlank().not()
    }

    private fun authWithEmailAndPassword(email: String, password: String) {
        sendUIEvent(LoginEvent.ShowLoading)
        viewModelScope.launch {
            repository.authWithEmailAndPassword(email, password) { result ->
                sendUIEvent(LoginEvent.LoginResult(result))
                sendUIEvent(LoginEvent.HideLoading)
            }
        }
    }

    private fun sendUIEvent(event: LoginEvent) {
        _viewState.value = event
    }
}
