package com.example.vinilosapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp.R
import com.example.vinilosapp.databinding.TrackItemBinding
import com.example.vinilosapp.models.Track
import com.example.vinilosapp.view.TrackFragmentDirections
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class TracksAdapter : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>(){
    var tracks :List<Track> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class TrackViewHolder(val viewDataBinding: TrackItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.track_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val withDataBinding: TrackItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            TrackViewHolder.LAYOUT,
            parent,
            false)
        return TrackViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {

        holder.viewDataBinding.also {
            it.track = tracks[position]
            val imageView: ImageView =  holder.viewDataBinding.root.findViewById(R.id.headerImage)
            Picasso.get()
                .load(tracks[position].cover)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imageView)
        }
        holder.viewDataBinding.root.setOnClickListener {
            val action = TrackFragmentDirections.actionTrackFragmentToTrackDetailFragment(tracks[position].trackId)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return tracks.size
    }
}