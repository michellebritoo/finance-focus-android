package br.com.michellebrito.financefocus.signup.domain

interface SignUpRepository {
    fun registerAccount(email: String, password: String, callback: (Boolean) -> Unit)
    suspend fun notifyNewAccount(authorization: String, onSuccess: () -> Unit, onError: (Throwable) -> Unit)
}

