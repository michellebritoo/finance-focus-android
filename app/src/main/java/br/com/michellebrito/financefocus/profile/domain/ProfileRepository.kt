package br.com.michellebrito.financefocus.profile.domain

import retrofit2.Response

interface ProfileRepository {
    suspend fun getUserDetails(): Response<UserDetailsModel>
    suspend fun deleteUser(onSuccess: () -> Unit, onError: () -> Unit)
    suspend fun logout(onSuccess: () -> Unit, onError: () -> Unit)
    suspend fun onEditProfile(
        model: EditUserDetailsModel,
        onSuccess: () -> Unit,
        onError: () -> Unit
    )
}
