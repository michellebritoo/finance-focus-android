package br.com.michellebrito.financefocus.signup.presentation.passwordstep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.signup.domain.SignUpRepository
import br.com.michellebrito.financefocus.util.extensions.isEmptyOrBlank
import br.com.michellebrito.financefocus.util.extensions.orEmpty
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpPasswordViewModel(private val repository: SignUpRepository) : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth
    private val _viewState = MutableLiveData<SignUpPasswordEvent>()
    val viewState: LiveData<SignUpPasswordEvent> = _viewState
    private var email = ""

    fun onInit(email: String) {
        this.email = email
    }

    fun onCreateAccountButtonClicked(password: String, confirmPassword: String) {
        when {
            isPasswordValid(password.trim()).not() -> return sendUIEvent(SignUpPasswordEvent.PasswordInvalid)
            isPasswordValid(confirmPassword.trim()).not() -> return sendUIEvent(SignUpPasswordEvent.ConfirmPasswordInvalid)
            (password.trim() != confirmPassword.trim()) -> return sendUIEvent(SignUpPasswordEvent.PasswordNotEquals)
            else -> createNewAccount(email, password)
        }
    }

    private fun createNewAccount(email: String, password: String) {
        viewModelScope.launch {
            sendUIEvent(SignUpPasswordEvent.ShowLoading)
            repository.registerAccount(email, password.trim()) { result ->
                if (result) {
                    onCreateUserWithSuccess()
                }
            }
        }
    }

    private fun onCreateUserWithSuccess() {
        viewModelScope.launch {
            try {
                val tokenResult = auth.currentUser?.getIdToken(true)?.awaitGetToken()
                val authorization = tokenResult?.token.orEmpty()

                repository.notifyNewAccount(
                    authorization = authorization,
                    onSuccess = {
                        sendUIEvent(SignUpPasswordEvent.RegisterResult(true))
                        sendUIEvent(SignUpPasswordEvent.HideLoading)
                    },
                    onError = {
                        sendUIEvent(SignUpPasswordEvent.ShowError)
                        sendUIEvent(SignUpPasswordEvent.HideLoading)
                    }
                )
            } catch (e: Exception) {
                sendUIEvent(SignUpPasswordEvent.ShowError)
                sendUIEvent(SignUpPasswordEvent.HideLoading)
            }
        }
    }

    suspend fun Task<GetTokenResult>.awaitGetToken(): GetTokenResult {
        return await()
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
