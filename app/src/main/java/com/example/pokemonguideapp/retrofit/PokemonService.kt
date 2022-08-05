package com.example.pokemonguideapp.retrofit

import com.example.pokemonguideapp.models.Pokemon
import com.example.pokemonguideapp.models.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon/{id}/")
    suspend fun searchPokemonById(@Path("id") id:Int): Response<Pokemon>

    @GET("pokemon/")
    suspend fun searchPokemonList(): Response<PokemonListResponse>

    @GET("pokemon")
    suspend fun searchPokemonListParams(@Query("offset") offset:Int,@Query("limit") limit:Int): Response<PokemonListResponse>

}