package com.example.pokemonguideapp

import com.example.pokemonguideapp.models.Pokemon

interface OnClickListener {
    fun onItemClick(pokemon: Pokemon)
}