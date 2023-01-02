package com.alpamedev.pokemonguideapp.models

import com.google.gson.annotations.SerializedName

data class GenerationResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("main_region")
    val mainRegion: ResultMainRegion,
    @SerializedName("name")
    val name: String,   //"generation-i"
    @SerializedName("pokemon_species")
    val pokemonSpecies: List<ResultPokemonSpecies>,
)
data class ResultMainRegion(
    val name: String,   //"kanto"
    val url: String     //"https://pokeapi.co/api/v2/region/1/"
)

data class ResultPokemonSpecies(
    val name: String,   //"bulbasaur"
    val url: String     //"https://pokeapi.co/api/v2/pokemon-species/1/"
)
