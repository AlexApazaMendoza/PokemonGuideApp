package com.example.pokemonguideapp.models

data class GenerationListResponse(
    val count: Long,
    val next: String? = null,
    val previous: String? = null,
    val results: List<ResultGeneration>
)

data class ResultGeneration (
    val name: String,   //"generation-i"
    val url: String     //"https://pokeapi.co/api/v2/generation/1/"
)
