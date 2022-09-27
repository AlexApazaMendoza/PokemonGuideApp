package com.example.pokemonguideapp.models

import com.google.gson.annotations.SerializedName

data class PokemonSpecieResponse(
    @SerializedName("color")
    val color: ResultColor,
    @SerializedName("generation")
    val generation: ResultGeneration,
    @SerializedName("id")
    val id: Long,       //1
    @SerializedName("name")
    val name: String,   //"bulbasaur"
    @SerializedName("varieties")
    val varieties: List<VarietiesResult>
)

data class ResultColor (
    @SerializedName("name")
    val name: String,   //"green"
    @SerializedName("url")
    val url: String     //"https://pokeapi.co/api/v2/pokemon-color/5/"
)

data class VarietiesResult (
    @SerializedName("is_default")
    val isDefault: String,   //true
    @SerializedName("pokemon")
    val pokemon: ResultPokemon
)