package com.example.pokemonguideapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokemonguideapp.R
import com.example.pokemonguideapp.databinding.PokemonDetailDialogBinding
import com.example.pokemonguideapp.databinding.PokemonTypeRowItemBinding
import com.example.pokemonguideapp.models.Type
import com.example.pokemonguideapp.toNamePokemonDisplay

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
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}