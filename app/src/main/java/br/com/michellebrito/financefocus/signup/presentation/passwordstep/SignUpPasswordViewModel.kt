package br.com.michellebrito.financefocus.signup.presentation.passwordstep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.signup.domain.SignUpRepository
import br.com.michellebrito.financefocus.util.extensions.isEmptyOrBlank
import kotlinx.coroutines.launch

class SignUpPasswordViewModel(private val repository: SignUpRepository) : ViewModel() {
    private var email = ""
    private val _viewState = MutableLiveData<SignUpPasswordEvent>()
    val viewState: LiveData<SignUpPasswordEvent> = _viewState
    fun onInit(email: String) {
        this.email = email
    }

    fun onCreateAccountButtonClicked(password: String, confirmPassword: String) {
        when {
            isPasswordValid(password.trim()).not() -> return sendUIEvent(SignUpPasswordEvent.PasswordInvalid)
            isPasswordValid(confirmPassword.trim()).not() -> return sendUIEvent(SignUpPasswordEvent.ConfirmPasswordInvalid)
            (password != confirmPassword) -> return sendUIEvent(SignUpPasswordEvent.PasswordNotEquals)
            else -> createNewAccount(email, password)
        }
    }

    private fun createNewAccount(email: String, password: String) {
        viewModelScope.launch {
            sendUIEvent(SignUpPasswordEvent.ShowLoading)
            repository.registerAccount(email, password.trim()) { result ->
                sendUIEvent(SignUpPasswordEvent.HideLoading)
                sendUIEvent(SignUpPasswordEvent.RegisterResult(result))
            }
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isEmptyOrBlank().not() && password.length >= MIN_VALUE
    }

    private fun sendUIEvent(event: SignUpPasswordEvent) {
        _viewState.value = event
    }

    private companion object {
        const val MIN_VALUE = 8
    }
}
