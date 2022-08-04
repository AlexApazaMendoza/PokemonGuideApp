package com.example.pokemonguideapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonguideapp.R
import com.example.pokemonguideapp.databinding.PokemonRowItemBinding
import com.example.pokemonguideapp.models.Pokemon

class PokemonAdapter(private val dataSet:MutableList<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){

    /***
     * Add inner in the class
     */
     inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val binding = PokemonRowItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_row_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.namePokemon.text = dataSet[position].name
            binding.weightPokemon.text = dataSet[position].weight.toString()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}