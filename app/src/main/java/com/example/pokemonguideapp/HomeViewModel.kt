package com.example.pokemonguideapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonguideapp.models.PokemonResponse

class HomeViewModel: ViewModel() {

    private val _pokemonList = MutableLiveData<MutableList<PokemonResponse>>(mutableListOf())
    val pokemonList: LiveData<MutableList<PokemonResponse>> = _pokemonList

    private val interactor: HomeInteractor

    init {
        interactor = HomeInteractor()
        _pokemonList.value = mutableListOf()
    }

    fun getPokemons(){
        interactor.getGenerationList { generationList ->
            interactor.getPokemonSpecieListByGenerationName(generationList.firstOrNull()?.name ?: ""){ pokemonSpeciesList ->
                interactor.getPokemonDataListByPokemonNameList(pokemonSpeciesList.map { it.name }.toMutableList()){ pokemonList ->
                    _pokemonList.postValue(pokemonList)
                }
            }
        }
    }


}