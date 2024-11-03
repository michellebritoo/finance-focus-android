package br.com.michellebrito.financefocus.common.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit {
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            retrofit = Retrofit.Builder()
                .baseUrl("https://financefocus.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit!!
    }
}
