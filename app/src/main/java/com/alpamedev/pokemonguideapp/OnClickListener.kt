package com.alpamedev.pokemonguideapp

import com.alpamedev.pokemonguideapp.models.PokemonResponse
import com.alpamedev.pokemonguideapp.models.ResultGeneration

interface OnClickListener {
    fun onItemPokemonClick(pokemon: PokemonResponse)
    fun onItemGenerationClick(generation: ResultGeneration)
}