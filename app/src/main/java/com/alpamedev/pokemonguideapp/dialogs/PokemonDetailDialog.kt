package com.alpamedev.pokemonguideapp.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.alpamedev.pokemonguideapp.R
import com.alpamedev.pokemonguideapp.adapters.PokemonTypeAdapter
import com.alpamedev.pokemonguideapp.databinding.PokemonDetailDialogBinding
import com.alpamedev.pokemonguideapp.models.PokemonResponse
import com.alpamedev.pokemonguideapp.toHeightPokemonDisplay
import com.alpamedev.pokemonguideapp.toNamePokemonDisplay
import com.alpamedev.pokemonguideapp.toWeightPokemonDisplay

class PokemonDetailDialog(var pokemon: PokemonResponse): DialogFragment() {

    private lateinit var mBinding : PokemonDetailDialogBinding
    private lateinit var mPokemonTypeAdapter : PokemonTypeAdapter
    private lateinit var mGridLayout: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        mBinding = PokemonDetailDialogBinding.inflate(inflater,container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpViews() {
        with(mBinding){
            tvNamePokemon.text = pokemon.name.toNamePokemonDisplay()
            orderPokemon.text = "#${pokemon.order.toString().padStart(3,'0')}"
            tvHeightPokemon.text = pokemon.height.toString().toHeightPokemonDisplay()
            tvWeightPokemon.text = pokemon.weight.toString().toWeightPokemonDisplay()

            root.setBackgroundResource(
                when(pokemon.types.first().type.name){
                    "steel" -> R.color.color_type_pokemon_steel_light
                    "water" -> R.color.color_type_pokemon_water_light
                    "bug" -> R.color.color_type_pokemon_bug_light
                    "dragon" -> R.color.color_type_pokemon_dragon_light
                    "electric" -> R.color.color_type_pokemon_electric_light
                    "ghost" -> R.color.color_type_pokemon_ghost_light
                    "fire" -> R.color.color_type_pokemon_fire_light
                    "fairy" -> R.color.color_type_pokemon_fairy_light
                    "ice" -> R.color.color_type_pokemon_ice_light
                    "fighting" -> R.color.color_type_pokemon_fighting_light
                    "normal" -> R.color.color_type_pokemon_normal_light
                    "grass" -> R.color.color_type_pokemon_grass_light
                    "psychic" -> R.color.color_type_pokemon_psychic_light
                    "dark" -> R.color.color_type_pokemon_dark_light
                    "ground" -> R.color.color_type_pokemon_ground_light
                    "poison" -> R.color.color_type_pokemon_poison_light
                    "flying" -> R.color.color_type_pokemon_flying_light
                    "rock" -> R.color.color_type_pokemon_rock_light
                    else -> R.color.white
                }
            )
        }

        context?.let {
            Glide.with(it)
                .load(pokemon.sprites.frontDefault)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(mBinding.imgPokemon)
        }

        mPokemonTypeAdapter = PokemonTypeAdapter(pokemon.types.toMutableList())
        mGridLayout = GridLayoutManager(requireContext(), if(pokemon.types.size > 1) 2 else 1)

        mBinding.rvTypePokemon.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mPokemonTypeAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}