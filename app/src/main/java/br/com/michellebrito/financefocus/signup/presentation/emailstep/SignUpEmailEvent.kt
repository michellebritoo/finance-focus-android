package br.com.michellebrito.financefocus.signup.presentation.emailstep

sealed class SignUpEmailEvent {
    data object EmailInvalid : SignUpEmailEvent()
    data object ConfirmEmailInvalid : SignUpEmailEvent()
    data object EmailNotEquals : SignUpEmailEvent()
    data object ValidateSuccess : SignUpEmailEvent()
}
