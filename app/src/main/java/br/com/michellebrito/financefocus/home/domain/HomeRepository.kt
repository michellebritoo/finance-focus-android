package br.com.michellebrito.financefocus.home.domain

interface HomeRepository {
    fun setAuthorizationToken(authorization: String)
    suspend fun sendDeviceToken(authorization: String, token: String)
}
