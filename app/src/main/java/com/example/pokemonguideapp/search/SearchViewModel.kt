package com.example.pokemonguideapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonguideapp.models.PokemonResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel:ViewModel() {

    val interactor: SearchInteractor

    private val _pokemon = MutableLiveData<PokemonResponse?>(null)
    val pokemon: LiveData<PokemonResponse?> = _pokemon

    private val _showProgressBar = MutableLiveData<Boolean>(false)
    val showProgressBar : MutableLiveData<Boolean> = _showProgressBar

    init {
        interactor = SearchInteractor()
    }

    fun searchPokemon(namePokemon: String){
        _showProgressBar.value = true
        CoroutineScope(Dispatchers.IO).launch{
            interactor.searchPokemonByName(namePokemon){
                _pokemon.postValue(it)
            }
            _showProgressBar.postValue(false)
        }
    }

}