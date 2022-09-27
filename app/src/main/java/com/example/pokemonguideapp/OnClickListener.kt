package com.example.pokemonguideapp

import com.example.pokemonguideapp.models.PokemonResponse
import com.example.pokemonguideapp.models.ResultGeneration

interface OnClickListener {
    fun onItemPokemonClick(pokemon: PokemonResponse)
    fun onItemGenerationClick(generation: ResultGeneration)
}