package br.com.michellebrito.financefocus.welcome.domain

interface WelcomeRepository {
    fun isLoggedUser(): Boolean
    fun setAuthorizationToken(authorization: String)
    fun getAuthorizationToken(): String
    suspend fun sendDeviceToken(token: String)
}
