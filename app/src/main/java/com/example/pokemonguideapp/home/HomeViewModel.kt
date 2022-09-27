package com.example.pokemonguideapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonguideapp.models.GenerationResponse
import com.example.pokemonguideapp.models.PokemonResponse
import com.example.pokemonguideapp.models.ResultGeneration

class HomeViewModel: ViewModel() {

    private val _pokemonList = MutableLiveData<MutableList<PokemonResponse>>(mutableListOf())
    val pokemonList: LiveData<MutableList<PokemonResponse>> = _pokemonList

    private val _generationList = MutableLiveData<MutableList<ResultGeneration>>(mutableListOf())
    val generationList: LiveData<MutableList<ResultGeneration>> = _generationList

    private val interactor: HomeInteractor

    init {
        interactor = HomeInteractor()
        _pokemonList.value = mutableListOf()
    }

    fun getPokemons(){
        interactor.getGenerationList { generationList ->
            _generationList.postValue(generationList)
            interactor.getPokemonSpecieListByGenerationName(generationList.firstOrNull()?.name ?: ""){ pokemonSpeciesList ->
                interactor.getPokemonDataListByPokemonNameList(pokemonSpeciesList.map { it.name }.toMutableList()){ pokemonList ->
                    _pokemonList.postValue(pokemonList)
                }
            }
        }
    }

    fun onItemGenerationClick(generation: ResultGeneration){
        interactor.getPokemonSpecieListByGenerationName(generation.name){ pokemonSpeciesList ->
            interactor.getPokemonDataListByPokemonNameList(pokemonSpeciesList.map { it.name }.toMutableList()){ pokemonList ->
                _pokemonList.postValue(pokemonList)
            }
        }
    }

}