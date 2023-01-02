package com.alpamedev.pokemonguideapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.alpamedev.pokemonguideapp.OnClickListener
import com.alpamedev.pokemonguideapp.R
import com.alpamedev.pokemonguideapp.databinding.PokemonRowItemBinding
import com.alpamedev.pokemonguideapp.models.PokemonResponse
import com.alpamedev.pokemonguideapp.toNamePokemonDisplay

class PokemonAdapter(private val dataSet:MutableList<PokemonResponse>, private var listener: OnClickListener) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    /***
     * Add inner in the class
     */
     inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val binding = PokemonRowItemBinding.bind(view)

        fun setListener(pokemon: PokemonResponse){
            binding.root.setOnClickListener {
                listener.onItemPokemonClick(pokemon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_row_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            setListener(dataSet[position])
            binding.namePokemon.text = dataSet[position].name.toNamePokemonDisplay()
            binding.orderPokemon.text = "#${dataSet[position].order.toString().padStart(3,'0')}"
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