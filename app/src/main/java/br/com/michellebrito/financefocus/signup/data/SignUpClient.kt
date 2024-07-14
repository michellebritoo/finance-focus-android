package br.com.michellebrito.financefocus.signup.data

import retrofit2.http.Header
import retrofit2.http.POST

interface SignUpClient {
    @POST(USER_CREATE)
    suspend fun notifyNewUser(
        @Header(HEADER_AUTHORIZATION) authorization: String
    )

    private companion object {
        const val USER_CREATE = "/user/create"
        const val HEADER_AUTHORIZATION = "authorization"
    }
}
