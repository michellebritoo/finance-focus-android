package br.com.michellebrito.financefocus.passwordrecovery.presentation

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.passwordrecovery.domain.PasswordRecoveryRepository
import kotlinx.coroutines.launch

class PasswordRecoveryViewModel(private val repository: PasswordRecoveryRepository) : ViewModel() {
    private val _viewState = MutableLiveData<PasswordRecoveryEvent>()
    val viewState: LiveData<PasswordRecoveryEvent> = _viewState

    fun onContinueButtonPressed(email: String) {
        if (isEmailValid(email).not()) {
            sendUIEvent(PasswordRecoveryEvent.EmailInvalid)
        } else {
            sendPasswordRecovery(email)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    }

    private fun sendPasswordRecovery(email: String) {
        viewModelScope.launch {
            sendUIEvent(PasswordRecoveryEvent.ShowLoading)
            repository.sendEmailToken(email) { result ->
                sendUIEvent(PasswordRecoveryEvent.HideLoading)
                onCompleteRequest(result)
            }
        }
    }

    private fun onCompleteRequest(isSuccess: Boolean) {
        if (isSuccess) {
            sendUIEvent(PasswordRecoveryEvent.SendWithSuccess)
        } else {
            sendUIEvent(PasswordRecoveryEvent.GenericError)
        }
    }

    private fun sendUIEvent(event: PasswordRecoveryEvent) {
        _viewState.value = event
    }
}
