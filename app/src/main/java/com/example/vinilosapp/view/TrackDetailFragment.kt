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
import com.example.vinilosapp.databinding.TrackDetailFragmentBinding
import com.example.vinilosapp.view.adapters.TrackDetailAdapter
import com.example.vinilosapp.viewmodels.TrackDetailViewModel


class TrackDetailFragment : Fragment() {
    private var _binding: TrackDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TrackDetailViewModel
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: TrackDetailAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TrackDetailFragmentBinding.inflate(inflater, container, false)
        viewModelAdapter = TrackDetailAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.trackRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity)
        val args: TrackDetailFragmentArgs by navArgs()
        Log.d("Args", args.trackId.toString())
        viewModel = ViewModelProvider(this, TrackDetailViewModel.Factory(activity.application, args.trackId)).get(
            TrackDetailViewModel::class.java)
        viewModel.track.observe(viewLifecycleOwner) {
            it.apply {
                if (this.trackId == trackId) {
                    viewModelAdapter!!.track = this
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