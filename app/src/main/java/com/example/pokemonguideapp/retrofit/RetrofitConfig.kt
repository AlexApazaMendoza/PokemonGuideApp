package com.example.pokemonguideapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    private val logging: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(OkHttpClient.Builder().addInterceptor(logging).build())
            .build()
    }

    /***
     * Interfaces
     */
    val pokemonService : PokemonService by lazy {
        retrofit.create(PokemonService::class.java)
    }
}