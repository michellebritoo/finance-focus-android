package br.com.michellebrito.financefocus.profile.data

import br.com.michellebrito.financefocus.profile.domain.EditUserDetailsModel
import br.com.michellebrito.financefocus.profile.domain.UserDetailsModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ProfileClient {

    @GET(USER_DETAILS)
    suspend fun getUserDetails(
        @Header(HEADER_AUTHORIZATION) authorization: String
    ): Response<UserDetailsModel>

    @POST(UPDATE_USER)
    suspend fun updateUserDetails(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Body model: EditUserDetailsModel
    )

    private companion object {
        const val USER_DETAILS = "/user/details"
        const val UPDATE_USER = "/user/update"
        const val HEADER_AUTHORIZATION = "authorization"
    }
}
