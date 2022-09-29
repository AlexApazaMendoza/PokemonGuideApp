package com.example.pokemonguideapp.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokemonguideapp.adapters.PokemonAdapter
import com.example.pokemonguideapp.adapters.PokemonTypeAdapter
import com.example.pokemonguideapp.databinding.PokemonDetailDialogBinding
import com.example.pokemonguideapp.models.PokemonResponse
import com.example.pokemonguideapp.toHeightPokemonDisplay
import com.example.pokemonguideapp.toNamePokemonDisplay
import com.example.pokemonguideapp.toWeightPokemonDisplay

class PokemonDetailDialog(var pokemon: PokemonResponse): DialogFragment() {

    private lateinit var mBinding : PokemonDetailDialogBinding
    private lateinit var mPokemonTypeAdapter : PokemonTypeAdapter
    private lateinit var mGridLayout: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        }

        context?.let {
            Glide.with(it)
                .load(pokemon.sprites.frontDefault)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(mBinding.imgPokemon)
        }

        mPokemonTypeAdapter = PokemonTypeAdapter(pokemon.types.toMutableList())
        mGridLayout = GridLayoutManager(requireContext(),2)

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