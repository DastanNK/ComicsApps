package com.example.comicsapp.Api

import com.example.comicsapp.CharactersApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("characters")
    suspend fun getApi(@Query("nameStartsWith") name: String): Call<CharactersApiResponse>
}