package com.example.pokemonguideapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://pokeapi.co/api/v2/")
            .build()
    }

    /***
     * Interfaces
     */
    val pokemonService : PokemonService by lazy {
        retrofit.create(PokemonService::class.java)
    }
}