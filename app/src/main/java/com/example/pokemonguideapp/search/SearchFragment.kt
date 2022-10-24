package com.example.pokemonguideapp.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokemonguideapp.R
import com.example.pokemonguideapp.adapters.PokemonStatAdapter
import com.example.pokemonguideapp.adapters.PokemonTypeAdapter
import com.example.pokemonguideapp.databinding.FragmentSearchBinding
import com.example.pokemonguideapp.models.PokemonResponse
import com.example.pokemonguideapp.toHeightPokemonDisplay
import com.example.pokemonguideapp.toNamePokemonDisplay
import com.example.pokemonguideapp.toWeightPokemonDisplay

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mBinding: FragmentSearchBinding
    private lateinit var mViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSearchBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        setUpViews()
    }

    private fun setUpViews() {
        mBinding.btnSearchPokemon.setOnClickListener {
            mViewModel.searchPokemon(mBinding.etNamePokemon.text.toString().lowercase().trim())
        }
    }

    private fun setUpViewModel() {
        mViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        mViewModel.pokemon.observe(viewLifecycleOwner){
            if(it != null){
                updatePokemonView(it)
                mBinding.clPokemon.visibility = View.VISIBLE
            }
        }
    }

    private fun updatePokemonView(pokemon: PokemonResponse) {
        with(mBinding){
            tvNamePokemon.text = pokemon.name.toNamePokemonDisplay()
            orderPokemon.text = "#${pokemon.order.toString().padStart(3,'0')}"
            tvHeightPokemon.text = pokemon.height.toString().toHeightPokemonDisplay()
            tvWeightPokemon.text = pokemon.weight.toString().toWeightPokemonDisplay()

            clPokemon.setBackgroundResource(
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

        val mPokemonTypeAdapter = PokemonTypeAdapter(pokemon.types.toMutableList())
        val mGridLayout =
            GridLayoutManager(requireContext(), if (pokemon.types.size > 1) 2 else 1)

        mBinding.rvTypePokemon.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mPokemonTypeAdapter
        }

        val mPokemonStatAdapter = PokemonStatAdapter(pokemon.stats.toMutableList())
        val mLinearLayout = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        mBinding.rvStatsPokemon.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout
            adapter = mPokemonStatAdapter
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}