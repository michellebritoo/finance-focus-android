package br.com.michellebrito.financefocus.passwordrecovery.domain

interface PasswordRecoveryRepository {
    fun sendEmailToken(email: String, callback: (Boolean) -> Unit)
}
