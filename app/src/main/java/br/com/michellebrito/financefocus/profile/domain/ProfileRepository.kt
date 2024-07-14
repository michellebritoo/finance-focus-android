package br.com.michellebrito.financefocus.profile.domain

import retrofit2.Response

interface ProfileRepository {
    suspend fun getUserDetails(): Response<UserDetailsModel>
}
