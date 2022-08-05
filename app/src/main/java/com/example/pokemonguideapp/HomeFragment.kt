package com.example.pokemonguideapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonguideapp.adapters.PokemonAdapter
import com.example.pokemonguideapp.databinding.FragmentHomeBinding
import com.example.pokemonguideapp.models.Pokemon
import com.example.pokemonguideapp.models.PokemonListResponse
import com.example.pokemonguideapp.models.ResultPokemon
import com.example.pokemonguideapp.retrofit.PokemonService
import com.example.pokemonguideapp.retrofit.RetrofitConfig.pokemonService
import kotlinx.coroutines.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(),OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mAdapter: PokemonAdapter
    private lateinit var mGridLayout: GridLayoutManager

    private var pokemons: MutableList<Pokemon> = mutableListOf()
    private var pokemonList: MutableList<ResultPokemon> = mutableListOf()

    private var job: Job? = null

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

        job = CoroutineScope(Dispatchers.IO).launch{
            getPokemonList()
            getPokemonData()
        }
    }

    private fun setUpViews() {
        pokemonList.clear()
        pokemons.clear()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){
        mAdapter = PokemonAdapter(pokemons,this)
        mGridLayout = GridLayoutManager(requireContext(),2)

        mBinding.rvPokemon.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }
    }

    private suspend fun getPokemonList(url:String? = null){
        if (url == null){
            val call = pokemonService.searchPokemonList()
            val response = call.body()
            if(call.isSuccessful){
                if (response != null){
                    pokemonList.addAll(response.results)
                    if(response.next != null){
                        getPokemonList(response.next.split("?")[1])
                    }
                }
            } else{
                Log.i("TestAlex","Error")
            }
        } else{
            val offset = url.split("offset=")[1].split("&")[0].toInt()
            val limit = url.split("limit=")[1].toInt()
            val call = pokemonService.searchPokemonListParams(offset, limit)
            val response = call.body()
            if(call.isSuccessful){
                if (response != null){
                    pokemonList.addAll(response.results)
                    if(response.next != null){
                        getPokemonList(response.next.split("?")[1])
                    }
                }
            } else{
                Log.i("TestAlex","Error")
            }
        }
    }

    private suspend fun getPokemonData() {
        if (pokemonList.isNotEmpty()){
                for (i in pokemonList){
                    val call = pokemonService.searchPokemonById(i.url.split("https://pokeapi.co/api/v2/pokemon/")[1].dropLast(1).toInt())
                    val response = call.body()
                    withContext(Dispatchers.Main){
                        if(call.isSuccessful){
                            if (response != null){
                                pokemons.add(response)
                                mAdapter.notifyDataSetChanged()
                            }
                        } else{
                            Toast.makeText(requireContext(), "Error",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }



    override fun onStop() {
        super.onStop()
        if(job != null){
            if(job!!.isActive){
                job!!.cancel()
            }
            job = null
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

    override fun onItemClick(pokemon: Pokemon) {
        Toast.makeText(requireContext(),"Item"+pokemon.name,Toast.LENGTH_SHORT).show()
    }
}