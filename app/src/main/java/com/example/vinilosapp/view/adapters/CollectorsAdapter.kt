package com.example.vinilosapp.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.vinilosapp.R
import com.example.vinilosapp.databinding.AlbumItemBinding

import com.example.vinilosapp.placeholder.PlaceholderContent.PlaceholderItem
import com.example.vinilosapp.databinding.CollectorItemBinding
import com.example.vinilosapp.models.Album
import com.example.vinilosapp.models.Collector
import com.example.vinilosapp.view.AlbumFragmentDirections
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CollectorsAdapter: RecyclerView.Adapter<CollectorsAdapter.CollectorViewHolder>(){

    var collectors :List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class CollectorViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorsAdapter.CollectorViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorsAdapter.CollectorViewHolder.LAYOUT,
            parent,
            false)
        return CollectorsAdapter.CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorsAdapter.CollectorViewHolder, position: Int) {

        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
            val action = AlbumFragmentDirections.actionAlbumFragmentToAlbumDetailFragment(collectors[position].collectorId)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = collectors.size
}