package com.example.pokemonguideapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemonguideapp.adapters.PokemonAdapter
import com.example.pokemonguideapp.databinding.FragmentHomeBinding
import com.example.pokemonguideapp.models.Pokemon
import com.example.pokemonguideapp.retrofit.RetrofitConfig.pokemonService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mAdapter: PokemonAdapter
    private lateinit var mGridLayout: GridLayoutManager
    private var pokemons: MutableList<Pokemon> = mutableListOf()

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
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()

        /***
         * Retrofit Local
         */
        /*val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PokemonService::class.java)
        val call = service.searchPokemonById(4)*/

        CoroutineScope(Dispatchers.IO).launch{
            val call = pokemonService.searchPokemonById(4)
            val pokemon = call.body()
            withContext(Dispatchers.Main){
                if(call.isSuccessful){
                    if (pokemon != null){
                        pokemons.clear()
                        pokemons.add(pokemon)
                        pokemons.add(pokemon)
                        pokemons.add(pokemon)
                        pokemons.add(pokemon)
                        mAdapter.notifyDataSetChanged()
                    } else{
                        Log.i("TestAlex","nulo")
                    }
                    Toast.makeText(requireContext(), "Exitoso, "+pokemons.count(),Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(requireContext(), "Error",Toast.LENGTH_SHORT).show()
                }
            }

        }


    }

    private fun setUpViews() {
        mAdapter = PokemonAdapter(pokemons)
        mGridLayout = GridLayoutManager(requireContext(),1)

        mBinding.rvPokemon.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}