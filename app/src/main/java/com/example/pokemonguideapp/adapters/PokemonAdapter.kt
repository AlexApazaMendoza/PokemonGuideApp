package com.example.pokemonguideapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokemonguideapp.R
import com.example.pokemonguideapp.databinding.PokemonRowItemBinding
import com.example.pokemonguideapp.models.Pokemon

class PokemonAdapter(private val dataSet:MutableList<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    /***
     * Add inner in the class
     */
     inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val binding = PokemonRowItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_row_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.namePokemon.text = dataSet[position].name
            Glide.with(mContext)
                .load(dataSet[position].sprites.frontDefault)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgPokemon)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}