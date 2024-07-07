package br.com.michellebrito.financefocus.profile.data

import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.profile.domain.ProfileRepository
import br.com.michellebrito.financefocus.profile.domain.UserDetailsModel
import retrofit2.Response

class ProfileRepositoryImpl(
    private val client: ProfileClient,
    private val preferenceStorage: PreferenceStorage
): ProfileRepository {

    override suspend fun getUserDetails(): Response<UserDetailsModel> {
        return client.getUserDetails(
            preferenceStorage.getString(AUTHORIZATION)
        )
    }

    private companion object {
        const val AUTHORIZATION = "authorization"
    }
}
