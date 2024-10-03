package com.example.comicsapp

import com.example.comicsapp.Api.Api
import com.example.comicsapp.Api.ApiRepo
import com.example.comicsapp.Api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideApiService(): ApiRepo {
        return ApiRepo(ApiService.getRetrofit().create(Api::class.java))
    }
}