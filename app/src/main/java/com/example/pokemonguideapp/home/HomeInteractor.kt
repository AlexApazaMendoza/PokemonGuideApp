package com.example.pokemonguideapp.home

import android.util.Log
import com.example.pokemonguideapp.models.PokemonResponse
import com.example.pokemonguideapp.models.ResultGeneration
import com.example.pokemonguideapp.models.ResultPokemonSpecies
import com.example.pokemonguideapp.retrofit.RetrofitConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeInteractor {

    fun getGenerationList(callback: (MutableList<ResultGeneration>) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitConfig.pokemonService.searchGenerationList()
            val response = call.body()
            if(call.isSuccessful){
                if (response != null){
                    callback(response.results.toMutableList())
                }
            } else {
                Log.i("TestAlex","Error - getGenerationList")
            }
        }
    }

    fun getPokemonSpecieListByGenerationName(name: String, callback: (MutableList<ResultPokemonSpecies>) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitConfig.pokemonService.searchGenerationByName(name)
            val response = call.body()
            if(call.isSuccessful){
                if (response != null){
                    callback(response.pokemonSpecies.toMutableList())
                }
            } else {
                Log.i("TestAlex","Error - getPokemonSpecieListByGenerationName")
            }
        }
    }

    fun getPokemonDataListByPokemonNameList(names: MutableList<String>, callback: (MutableList<PokemonResponse>) -> Unit){
        var pokemonDataList = mutableListOf<PokemonResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            for (p in names){
                val call = RetrofitConfig.pokemonService.searchPokemonByName(p)
                val response = call.body()
                if(call.isSuccessful){
                    if (response != null){
                        pokemonDataList.add(response)
                        callback(pokemonDataList)
                    }
                } else {
                    Log.i("TestAlex","Error - getPokemonDataListByNameList (${p})")
                }
            }
        }
    }

    suspend fun getPokemonDataListByGeneration(name: String, callback: (MutableList<PokemonResponse>) -> Unit){
        val callGeneration = RetrofitConfig.pokemonService.searchGenerationByName(name)
        val responseGeneration = callGeneration.body()
        if(callGeneration.isSuccessful){
            if (responseGeneration != null){
                val pokemonDataList = mutableListOf<PokemonResponse>()
                for (p in responseGeneration.pokemonSpecies.toMutableList()){
                    val callPokemon = RetrofitConfig.pokemonService.searchPokemonByName(p.name)
                    val responsePokemon = callPokemon.body()
                    if(callPokemon.isSuccessful){
                        if (responsePokemon != null){
                            pokemonDataList.add(responsePokemon)
                            callback(pokemonDataList)
                        }
                    } else {
                        Log.i("TestAlex","Error - getPokemonDataListByNameList (${p})")
                    }
                }
            }
        } else {
            Log.i("TestAlex","Error - getPokemonDataListByGeneration")
        }
    }
}