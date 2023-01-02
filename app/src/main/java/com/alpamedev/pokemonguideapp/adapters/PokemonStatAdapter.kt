package com.alpamedev.pokemonguideapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alpamedev.pokemonguideapp.R
import com.alpamedev.pokemonguideapp.databinding.PokemonStatRowItemBinding
import com.alpamedev.pokemonguideapp.models.Stat

class PokemonStatAdapter(private val dataSet:MutableList<Stat>):RecyclerView.Adapter<PokemonStatAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)  {
        val mBinding = PokemonStatRowItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_stat_row_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            mBinding.tvNameStatPokemon.text = dataSet[position].stat.name.uppercase()
            mBinding.pbStatPokemon.apply {
                progress = dataSet[position].baseStat.toInt() / 3
                setIndicatorColor(
                    when(dataSet[position].stat.name){
                        "hp" -> ContextCompat.getColor(mContext, R.color.color_stat_pokemon_hp)
                        "attack" -> ContextCompat.getColor(mContext, R.color.color_stat_pokemon_attack)
                        "defense" -> ContextCompat.getColor(mContext, R.color.color_stat_pokemon_defense)
                        "special-attack" -> ContextCompat.getColor(mContext, R.color.color_stat_pokemon_special_attack)
                        "special-defense" -> ContextCompat.getColor(mContext, R.color.color_stat_pokemon_special_defense)
                        "speed" -> ContextCompat.getColor(mContext, R.color.color_stat_pokemon_speed)
                        else -> ContextCompat.getColor(mContext, R.color.color_stat_pokemon_hp)
                    }
                )
            }
            mBinding.tvValueStatPokemon.text = "${dataSet[position].baseStat} / 300"
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}