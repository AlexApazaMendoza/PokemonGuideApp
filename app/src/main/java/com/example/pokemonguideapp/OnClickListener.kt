package com.example.pokemonguideapp

import com.example.pokemonguideapp.models.PokemonResponse

interface OnClickListener {
    fun onItemClick(pokemon: PokemonResponse)
}