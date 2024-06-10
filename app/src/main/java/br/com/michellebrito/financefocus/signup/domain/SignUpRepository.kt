package br.com.michellebrito.financefocus.signup.domain

interface SignUpRepository {
    fun registerAccount(email: String, password: String, callback: (Boolean) -> Unit)
}

