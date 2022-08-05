package com.example.pokemonguideapp.retrofit

import com.example.pokemonguideapp.models.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{id}/")
    suspend fun searchPokemonById(@Path("id") id:Int): Response<Pokemon>

}