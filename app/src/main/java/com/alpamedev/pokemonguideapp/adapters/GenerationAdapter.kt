package com.alpamedev.pokemonguideapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alpamedev.pokemonguideapp.OnClickListener
import com.alpamedev.pokemonguideapp.R
import com.alpamedev.pokemonguideapp.databinding.GenerationRowItemBinding
import com.alpamedev.pokemonguideapp.models.ResultGeneration

class GenerationAdapter(private val dataSet:MutableList<ResultGeneration>, private var listener: OnClickListener):
    RecyclerView.Adapter<GenerationAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mBinding = GenerationRowItemBinding.bind(view)

        fun setListener(resultGeneration: ResultGeneration){
            mBinding.root.setOnClickListener {
                listener.onItemGenerationClick(resultGeneration)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.generation_row_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            setListener(dataSet[position])
            mBinding.tvGenerationTitle.text = dataSet[position].name
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}