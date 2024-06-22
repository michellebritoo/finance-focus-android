package br.com.michellebrito.financefocus.passwordrecovery.presentation

sealed class PasswordRecoveryEvent {
    data object ShowLoading: PasswordRecoveryEvent()
    data object HideLoading: PasswordRecoveryEvent()
    data object EmailInvalid: PasswordRecoveryEvent()
    data object GenericError: PasswordRecoveryEvent()
    data object SendWithSuccess: PasswordRecoveryEvent()
}
