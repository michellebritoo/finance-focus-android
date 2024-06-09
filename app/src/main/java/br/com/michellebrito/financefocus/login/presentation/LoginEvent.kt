package br.com.michellebrito.financefocus.login.presentation

sealed class LoginEvent {
    data object EmailInvalid: LoginEvent()
    data object PasswordInvalid: LoginEvent()
    data object ShowLoading: LoginEvent()
    data object HideLoading: LoginEvent()
    data class LoginResult(val isSuccess: Boolean) : LoginEvent()
}
