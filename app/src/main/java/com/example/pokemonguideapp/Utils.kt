package com.example.pokemonguideapp

fun String.toNamePokemonDisplay():String{
    return if(this.endsWith("-m")){
        this.replace("-m"," ♂")
    } else if(this.endsWith("-f")){
        this.replace("-f"," ♀")
    } else {
        this
    }
}