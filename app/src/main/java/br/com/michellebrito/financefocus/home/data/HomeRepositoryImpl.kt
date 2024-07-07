package br.com.michellebrito.financefocus.home.data

import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.home.domain.HomeRepository

class HomeRepositoryImpl(
    private val preferenceStorage: PreferenceStorage,
    private val client: HomeClient
): HomeRepository {
    override fun setAuthorizationToken(authorization: String) {
        preferenceStorage.putString(AUTHORIZATION, authorization)
    }

    override suspend fun sendDeviceToken(authorization: String, token: String) {
        client.sendDeviceToken(authorization, token)
    }

    private companion object {
        const val AUTHORIZATION = "authorization"
    }
}