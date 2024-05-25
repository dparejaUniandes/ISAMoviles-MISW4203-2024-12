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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp.viewmodels.CollectorDetailViewModel
import com.example.vinilosapp.databinding.CollectorDetailFragmentBinding
import com.example.vinilosapp.view.adapters.CollectorDetailAdapter
import com.example.vinilosapp.viewmodels.ArtistDetailViewModel

class CollectorDetailFragment : Fragment() {

    private var _binding: CollectorDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CollectorDetailViewModel
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: CollectorDetailAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CollectorDetailFragmentBinding.inflate(inflater, container, false)
        viewModelAdapter = CollectorDetailAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.collectorDetailRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity)
        val args: CollectorDetailFragmentArgs by navArgs()
        Log.d("Args", args.collectorId.toString())
        viewModel =
            ViewModelProvider(this, CollectorDetailViewModel.Factory(activity.application, args.collectorId)).get(
                    CollectorDetailViewModel::class.java)
        viewModel.collector.observe(viewLifecycleOwner) {
            it.apply {
                if (this.collectorId == collectorId) {
                    viewModelAdapter!!.collector = this
                    (getActivity() as AppCompatActivity?)!!.supportActionBar!!.title = "Asociar Track"
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