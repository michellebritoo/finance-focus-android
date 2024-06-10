package br.com.michellebrito.financefocus.signup.presentation.emailstep

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.michellebrito.financefocus.common.presentation.Event

class SignUpEmailViewModel : ViewModel() {
    private val _viewState = MutableLiveData<Event<SignUpEmailEvent>>()
    val viewState: LiveData<Event<SignUpEmailEvent>> get() = _viewState

    fun onContinueButtonPressed(email: String, confirmEmail: String) {
        when {
            isEmailValid(email).not() -> return sendUIEvent(SignUpEmailEvent.EmailInvalid)
            isEmailValid(confirmEmail).not() -> return sendUIEvent(SignUpEmailEvent.ConfirmEmailInvalid)
            (email.trim() == confirmEmail.trim()).not() -> return sendUIEvent(SignUpEmailEvent.EmailNotEquals)
            else -> sendUIEvent(SignUpEmailEvent.ValidateSuccess)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    }

    private fun sendUIEvent(event: SignUpEmailEvent) {
        _viewState.value = Event(event)
    }
}
