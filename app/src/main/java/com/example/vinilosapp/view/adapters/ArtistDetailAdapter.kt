package com.example.vinilosapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp.R
import com.example.vinilosapp.databinding.ArtistDetailItemBinding
import com.example.vinilosapp.models.Artist

class ArtistDetailAdapter : RecyclerView.Adapter<ArtistDetailAdapter.ArtistDetailViewHolder>() {

    var artist: Artist = Artist()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class ArtistDetailViewHolder(val viewDataBinding: ArtistDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_detail_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistDetailViewHolder {
        val withDataBinding: ArtistDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistDetailViewHolder.LAYOUT,
            parent,
            false)
        return ArtistDetailViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ArtistDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            artist.birthDate = artist.birthDate.split("T")[0]
            it.artist = artist
        }
    }
}