package com.alpamedev.pokemonguideapp

fun String.toNamePokemonDisplay():String{
    return if(this.endsWith("-m")){
        this.replace("-m"," ♂")
    } else if(this.endsWith("-f")){
        this.replace("-f"," ♀")
    } else {
        this
    }
}

fun String.toHeightPokemonDisplay():String{
    return "$this m."
}

fun String.toWeightPokemonDisplay():String{
    return "$this kg."
}