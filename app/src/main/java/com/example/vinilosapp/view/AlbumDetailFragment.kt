package com.example.vinilosapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp.databinding.AlbumDetailFragmentBinding
import com.example.vinilosapp.view.adapters.AlbumDetailAdapter
import com.example.vinilosapp.viewmodels.AlbumDetailViewModel


class AlbumDetailFragment : Fragment() {
    private var _binding: AlbumDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumDetailViewModel
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: AlbumDetailAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumDetailFragmentBinding.inflate(inflater, container, false)
        viewModelAdapter = AlbumDetailAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.albumRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity)
        val args: AlbumDetailFragmentArgs by navArgs()
        Log.d("Args", args.albumId.toString())
        viewModel = ViewModelProvider(this, AlbumDetailViewModel.Factory(activity.application, args.albumId)).get(
            AlbumDetailViewModel::class.java)
        viewModel.album.observe(viewLifecycleOwner) {
            it.apply {
                if (this.albumId == albumId) {
                    viewModelAdapter!!.album = this
                    (getActivity() as AppCompatActivity?)!!.supportActionBar!!.title = this.name
                }
            }
        }
        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }
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