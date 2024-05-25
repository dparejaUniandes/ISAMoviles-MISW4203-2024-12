package com.example.vinilosapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp.R
import com.example.vinilosapp.databinding.AlbumDetailItemBinding
import com.example.vinilosapp.models.Album
import com.example.vinilosapp.view.AlbumDetailFragmentDirections
import com.example.vinilosapp.view.CollectorFragmentDirections

class AlbumDetailAdapter : RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailViewHolder>() {

    var album: Album = Album()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class AlbumDetailViewHolder(val viewDataBinding: AlbumDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_detail_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val withDataBinding: AlbumDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumDetailViewHolder.LAYOUT,
            parent,
            false)
        return AlbumDetailViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = album
        }
        val action = AlbumDetailFragmentDirections
                .actionAlbumDetailFragmentToTrackAssociateFragment(album.albumId)
        val postButton = holder.viewDataBinding.root.findViewById<Button>(R.id.associate_track_album_button)
        postButton.setOnClickListener {
             holder.viewDataBinding.root.findNavController().navigate(action)
        }

    }
}