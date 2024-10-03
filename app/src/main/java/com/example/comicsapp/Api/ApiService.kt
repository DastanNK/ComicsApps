package com.example.comicsapp.Api

import android.util.Log
import com.example.comicsapp.BuildConfig
import com.example.comicsapp.getHash
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object ApiService {
    private const val BASE_URL = "http://gateway.marvel.com/v1/public/"

    fun getRetrofit(): Retrofit {
        val ts = System.currentTimeMillis().toString()
        val ApiSecret = BuildConfig.MARVEL_SECRET
        val ApiKey = BuildConfig.MARVEL_KEY
        val hash = getHash(ts, ApiSecret, ApiKey)
        Log.d("Marvel", ts)
        Log.d("Marvel", ApiKey)
        Log.d("Marvel", ApiSecret)
        Log.d("Marvel", hash)
        val clientInterceptor = Interceptor { chain ->
            var request: Request = chain.request()
            val url: HttpUrl = request.url.newBuilder()
                .addQueryParameter("ts", ts)
                .addQueryParameter("apikey", ApiKey)
                .addQueryParameter("hash",hash)
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
        val client=OkHttpClient.Builder().addInterceptor(clientInterceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }

    //val api:Api=getRetrofit().create(Api::class.java)

}