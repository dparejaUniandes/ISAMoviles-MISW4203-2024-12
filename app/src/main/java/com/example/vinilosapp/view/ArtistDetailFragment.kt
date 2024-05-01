package com.example.vinilosapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp.databinding.ArtistDetailFragmentBinding
import com.example.vinilosapp.models.Artist
import com.example.vinilosapp.view.adapters.ArtistDetailAdapter
import com.example.vinilosapp.viewmodels.ArtistDetailViewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import com.example.vinilosapp.view.adapters.AlbumsAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [ArtistDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArtistDetailFragment : Fragment() {
    private var artistId: Int = 0

    private var _binding: ArtistDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ArtistDetailViewModel
    private lateinit var recyclerView: RecyclerView
    private var artistDetailAdapter: ArtistDetailAdapter? = null
    private var albumAdapter: AlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArtistDetailFragmentBinding.inflate(inflater, container, false)
        artistDetailAdapter = ArtistDetailAdapter()
        albumAdapter = AlbumsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.artistDetailRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ConcatAdapter(artistDetailAdapter, albumAdapter)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity)
        val args: ArtistDetailFragmentArgs by navArgs()
        Log.d("Args", args.artistId.toString())
        viewModel = ViewModelProvider(this, ArtistDetailViewModel.Factory(activity.application, args.artistId)).get(
            ArtistDetailViewModel::class.java)
        viewModel.artist.observe(viewLifecycleOwner, Observer<Artist> {
            it.apply {
                if(this.artistId == artistId){
                    artistDetailAdapter!!.artist = this

                    albumAdapter!!.albums = this.albums
                    (getActivity() as AppCompatActivity?)!!.supportActionBar!!.title = this.name
                }
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }


}