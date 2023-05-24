package com.example.triviamaniac.network

import com.example.triviamaniac.QuestionResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL =
    "https://opentdb.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface TriviaApiService{
    @GET("/api.php")
    suspend fun getQuestions(
        @Query("amount") limit: Int = 10,
        @Query("category") category: Int? = null,
        @Query("difficulty") difficulty: String? = null,
        @Query("type") type: String = "multiple"
    ):QuestionResponse
}

object TriviaApi {
    val retrofitService : TriviaApiService by lazy {
        retrofit.create(TriviaApiService::class.java) }
}

