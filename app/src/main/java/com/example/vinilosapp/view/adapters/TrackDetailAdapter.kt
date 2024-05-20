package com.example.vinilosapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp.R
import com.example.vinilosapp.databinding.TrackDetailItemBinding
import com.example.vinilosapp.models.Track

class TrackDetailAdapter : RecyclerView.Adapter<TrackDetailAdapter.TrackDetailViewHolder>() {

    var track: Track = Track()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class TrackDetailViewHolder(val viewDataBinding: TrackDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.track_detail_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackDetailViewHolder {
        val withDataBinding: TrackDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            TrackDetailViewHolder.LAYOUT,
            parent,
            false)
        return TrackDetailViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: TrackDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.track = track
        }
    }
}