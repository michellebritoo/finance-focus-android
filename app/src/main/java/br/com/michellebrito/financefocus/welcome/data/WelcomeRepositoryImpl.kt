package br.com.michellebrito.financefocus.welcome.data

import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.welcome.domain.WelcomeRepository

class WelcomeRepositoryImpl(private val preferenceStorage: PreferenceStorage): WelcomeRepository {
    override fun isLoggedUser(): Boolean {
        return preferenceStorage.getBoolean(IS_LOGGED.plus(getCurrentUser()))
    }

    private fun getCurrentUser() = preferenceStorage.getString(CURRENT_USER)

    private companion object {
        const val IS_LOGGED = "is_logged"
        const val CURRENT_USER = "current_user"
    }
}
