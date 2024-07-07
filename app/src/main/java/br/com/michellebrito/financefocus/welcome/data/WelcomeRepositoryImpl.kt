package br.com.michellebrito.financefocus.welcome.data

import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.welcome.domain.WelcomeRepository

class WelcomeRepositoryImpl(
    private val preferenceStorage: PreferenceStorage,
    private val client: WelcomeClient
): WelcomeRepository {

    override fun isLoggedUser(): Boolean {
        return preferenceStorage.getBoolean(IS_LOGGED.plus(getCurrentUser()))
    }

    override fun setAuthorizationToken(authorization: String) {
        preferenceStorage.putString(AUTHORIZATION, authorization)
    }

    override fun getAuthorizationToken(): String {
        return preferenceStorage.getString(AUTHORIZATION)
    }

    override suspend fun sendDeviceToken(token: String) {
        client.sendDeviceToken(getAuthorizationToken(), token)
    }

    private fun getCurrentUser() = preferenceStorage.getString(CURRENT_USER)

    private companion object {
        const val IS_LOGGED = "is_logged"
        const val CURRENT_USER = "current_user"
        const val AUTHORIZATION = "authorization"
    }
}
