package com.example.pokemonguideapp.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonguideapp.OnClickListener
import com.example.pokemonguideapp.adapters.GenerationAdapter
import com.example.pokemonguideapp.adapters.PokemonAdapter
import com.example.pokemonguideapp.databinding.FragmentHomeBinding
import com.example.pokemonguideapp.dialogs.PokemonDetailDialog
import com.example.pokemonguideapp.models.*
import com.example.pokemonguideapp.retrofit.PokemonService
import com.example.pokemonguideapp.retrofit.RetrofitConfig.pokemonService
import kotlinx.coroutines.*
import okhttp3.internal.notify

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mViewModel: HomeViewModel

    private lateinit var mPokemonAdapter: PokemonAdapter
    private lateinit var mGridLayout: GridLayoutManager

    private lateinit var mGenerationAdapter: GenerationAdapter
    private lateinit var mLayoutManager:LinearLayoutManager
    private var generations: MutableList<ResultGeneration> = mutableListOf()


    private var pokemons: MutableList<PokemonResponse> = mutableListOf()

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
        mBinding = FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        setUpRecyclerView()
        initializePokemonList()
    }

    private fun initializePokemonList() {
        if(pokemons.isEmpty()){ mViewModel.getPokemons() }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpViewModel() {
        mViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        mViewModel.pokemonList.observe(viewLifecycleOwner){
            pokemons.clear()
            pokemons.addAll(it)
            mPokemonAdapter.notifyDataSetChanged()
        }
        mViewModel.generationList.observe(viewLifecycleOwner){
            generations.clear()
            generations.addAll(it)
            mGenerationAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView(){
        mPokemonAdapter = PokemonAdapter(pokemons,this)
        mGridLayout = GridLayoutManager(requireContext(),2)

        mBinding.rvPokemon.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mPokemonAdapter
        }

        mGenerationAdapter = GenerationAdapter(generations,this)
        mLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)

        mBinding.rvGeneration.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = mGenerationAdapter
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

    override fun onItemPokemonClick(pokemon: PokemonResponse) {
        //Toast.makeText(requireContext(),"Item: "+pokemon.name,Toast.LENGTH_SHORT).show()
        PokemonDetailDialog(pokemon).show(requireActivity().supportFragmentManager,"MyDialog")
    }

    override fun onItemGenerationClick(generation: ResultGeneration) {
        mViewModel.onItemGenerationClick(generation)
    }
}