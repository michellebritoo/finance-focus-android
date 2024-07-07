package br.com.michellebrito.financefocus.profile.data

import br.com.michellebrito.financefocus.profile.domain.UserDetailsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileClient {

    @GET(USER_DETAILS)
    suspend fun getUserDetails(
        @Header(HEADER_AUTHORIZATION) authorization: String
    ): Response<UserDetailsModel>

    private companion object {
        const val USER_DETAILS = "/user/details"
        const val HEADER_AUTHORIZATION = "authorization"
    }
}
