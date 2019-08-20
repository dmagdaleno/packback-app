package br.com.boomerang.packbackapp.repository.web

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class PackbackService {

    companion object {
        private const val API_BASE_URL = "http://35.247.211.12:8080/packback/api/"
    }

    private val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())

    private var retrofit = builder.build()

    private val httpClient = OkHttpClient.Builder()

    fun <S : Any> cria(serviceClass: Class<S>): S? {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(loggingInterceptor)

        builder.client(httpClient.build())

        retrofit = builder.build()

        return retrofit?.create(serviceClass)
    }
}