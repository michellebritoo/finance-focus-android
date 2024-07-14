package br.com.michellebrito.financefocus.home.data

import retrofit2.http.Header
import retrofit2.http.POST

interface HomeClient {

    @POST(REFRESH_DEVICE_TOKEN)
    suspend fun sendDeviceToken(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Header(HEADER_DEVICE_TOKEN) token: String
    )

    private companion object {
        const val REFRESH_DEVICE_TOKEN = "/refresh/device/token"
        const val HEADER_AUTHORIZATION = "authorization"
        const val HEADER_DEVICE_TOKEN = "deviceToken"
    }
}
