package com.example.pokemonguideapp.models

data class PokemonListResponse(
    val count: Long,
    val next: String? = null,
    val previous: String? = null,
    val results: List<ResultPokemon>
)

data class ResultPokemon (
    val name: String,   //"bulbasaur"
    val url: String     //"https://pokeapi.co/api/v2/pokemon/1/"
)
