package com.example.comicsapp.Api

import android.util.Log
import com.example.comicsapp.CharactersApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class ApiRepo(private val api: Api) {
    val characters = MutableStateFlow<NetworkResult<CharactersApiResponse>>(NetworkResult.Initial())
    suspend fun query(query: String) {
        characters.value = NetworkResult.Loading()
        //Log.d("","Before delay")
        //Thread.sleep(3000)
        Log.d("","Before delay")
        //Log.d("", api.getApi(query).toString()?:"hello")
        api
            .getApi(query)
            .enqueue(object : Callback<CharactersApiResponse> {
            override fun onResponse(p0: Call<CharactersApiResponse>, response: Response<CharactersApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        characters.value = NetworkResult.Success(it)
                    }
                } else {
                    characters.value = NetworkResult.Error(response.message())
                }
            }

            override fun onFailure(p0: Call<CharactersApiResponse>, t: Throwable) {
                t.localizedMessage.let {
                    characters.value = NetworkResult.Error(it)
                }
                //Log.d("","lol")
                t.printStackTrace()
            }

        })
    }
}