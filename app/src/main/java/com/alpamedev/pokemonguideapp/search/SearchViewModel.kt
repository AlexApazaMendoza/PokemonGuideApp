package com.alpamedev.pokemonguideapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpamedev.pokemonguideapp.models.PokemonResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel:ViewModel() {

    val interactor: SearchInteractor

    private val _pokemon = MutableLiveData<PokemonResponse?>(null)
    val pokemon: LiveData<PokemonResponse?> = _pokemon

    private val _showProgressBar = MutableLiveData<Boolean>(false)
    val showProgressBar : MutableLiveData<Boolean> = _showProgressBar

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    init {
        interactor = SearchInteractor()
    }

    fun searchPokemon(namePokemon: String){
        _showProgressBar.value = true
        CoroutineScope(Dispatchers.IO+coroutineExceptionHandler).launch{
            interactor.searchPokemonByName(namePokemon){
                _pokemon.postValue(it)
            }
            _showProgressBar.postValue(false)
        }
    }

}