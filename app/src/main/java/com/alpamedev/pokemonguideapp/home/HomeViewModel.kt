package com.alpamedev.pokemonguideapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpamedev.pokemonguideapp.models.PokemonResponse
import com.alpamedev.pokemonguideapp.models.ResultGeneration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _pokemonList = MutableLiveData<MutableList<PokemonResponse>>(mutableListOf())
    val pokemonList: LiveData<MutableList<PokemonResponse>> = _pokemonList

    private val _generationList = MutableLiveData<MutableList<ResultGeneration>>(mutableListOf())
    val generationList: LiveData<MutableList<ResultGeneration>> = _generationList

    var job: Job? = null

    private val interactor: HomeInteractor

    init {
        interactor = HomeInteractor()
        _pokemonList.value = mutableListOf()
    }

    fun getPokemons(){
        interactor.getGenerationList { generationList ->
            _generationList.postValue(generationList)
            job = CoroutineScope(Dispatchers.IO).launch{
                getPokemonDataListByGeneration(generationList.first())
            }
        }
    }

    fun onItemGenerationClick(generation: ResultGeneration){
        if(job != null){
            if(job!!.isActive){
                job!!.cancel()
                job = null
            }
        }
        job = CoroutineScope(Dispatchers.IO).launch{
            getPokemonDataListByGeneration(generation)
        }
    }

    private suspend fun getPokemonDataListByGeneration(generation: ResultGeneration){
        interactor.getPokemonDataListByGeneration(generation.name){ pokemonList ->
            _pokemonList.postValue(pokemonList)
        }
    }

}