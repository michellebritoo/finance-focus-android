package br.com.michellebrito.financefocus.signup.presentation.passwordstep

sealed class SignUpPasswordEvent {
    data object PasswordInvalid : SignUpPasswordEvent()
    data object ConfirmPasswordInvalid : SignUpPasswordEvent()
    data object PasswordNotEquals : SignUpPasswordEvent()
    data object ShowLoading: SignUpPasswordEvent()
    data object HideLoading: SignUpPasswordEvent()
    data class RegisterResult(val isSuccess: Boolean) : SignUpPasswordEvent()
}