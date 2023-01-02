package com.alpamedev.pokemonguideapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alpamedev.pokemonguideapp.R
import com.alpamedev.pokemonguideapp.databinding.PokemonTypeRowItemBinding
import com.alpamedev.pokemonguideapp.models.Type

class PokemonTypeAdapter(private val dataSet:MutableList<Type>, )
    : RecyclerView.Adapter<PokemonTypeAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val binding = PokemonTypeRowItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_type_row_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.tvTypePokemonTitle.text = dataSet[position].type.name
            binding.root.setBackgroundResource(
                when(dataSet[position].type.name){
                    "steel" -> R.color.color_type_pokemon_steel
                    "water" -> R.color.color_type_pokemon_water
                    "bug" -> R.color.color_type_pokemon_bug
                    "dragon" -> R.color.color_type_pokemon_dragon
                    "electric" -> R.color.color_type_pokemon_electric
                    "ghost" -> R.color.color_type_pokemon_ghost
                    "fire" -> R.color.color_type_pokemon_fire
                    "fairy" -> R.color.color_type_pokemon_fairy
                    "ice" -> R.color.color_type_pokemon_ice
                    "fighting" -> R.color.color_type_pokemon_fighting
                    "normal" -> R.color.color_type_pokemon_normal
                    "grass" -> R.color.color_type_pokemon_grass
                    "psychic" -> R.color.color_type_pokemon_psychic
                    "dark" -> R.color.color_type_pokemon_dark
                    "ground" -> R.color.color_type_pokemon_ground
                    "poison" -> R.color.color_type_pokemon_poison
                    "flying" -> R.color.color_type_pokemon_flying
                    "rock" -> R.color.color_type_pokemon_rock
                    else -> R.color.white
                }
            )
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}