package br.com.michellebrito.financefocus.login.domain

interface LoginRepository {
    fun authWithEmailAndPassword(email: String, password: String, callback: (Boolean) -> Unit)
}
