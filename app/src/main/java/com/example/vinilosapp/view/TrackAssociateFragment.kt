package com.example.vinilosapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp.databinding.TrackAssociateFragmentBinding
import com.example.vinilosapp.viewmodels.TrackAssociateViewModel
import com.google.android.material.textfield.TextInputEditText

class TrackAssociateFragment : Fragment() {
    private var _binding: TrackAssociateFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TrackAssociateViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner
    private var counterDialogPressButton : Int = 0
    private var allFieldsAreValid : Boolean = true
    private var messageToDisplay : String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TrackAssociateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*recyclerView = binding.trackAssociateRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter*/
        super.onViewCreated(view, savedInstanceState)
        val associateButton: Button = requireView().findViewById(com.example.vinilosapp.R.id.associate_track_button)
        val activity = requireNotNull(this.activity)
        val args: AlbumDetailFragmentArgs by navArgs()
        Log.d("Args", args.albumId.toString())
        viewModel = ViewModelProvider(this, TrackAssociateViewModel.Factory(activity.application, args.albumId)).get(
            TrackAssociateViewModel::class.java)
        associateButton.setOnClickListener {
            Log.d("Button", "pressed")
            val nameTxt : TextInputEditText = view.findViewById(com.example.vinilosapp.R.id.txt_track_name)
            val durationTxt : TextInputEditText = view.findViewById(com.example.vinilosapp.R.id.txt_track_duration)
            validateFields(nameTxt.text.toString(), durationTxt.text.toString())
            Log.d("Message", messageToDisplay)
            if (allFieldsAreValid) {
                viewModel.refreshDataFromNetwork(nameTxt.text.toString(), durationTxt.text.toString())
                viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
                    if (isNetworkError) {
                        onNetworkError()
                    } else {
                        AlertDialog.Builder(context)
                            .setTitle("Éxito")
                            .setMessage(messageToDisplay)
                            .setPositiveButton("OK") { dialog, whichButton ->
                                dialog.dismiss()
                                counterDialogPressButton++
                                if (counterDialogPressButton >= 2) {
                                    val action =
                                        TrackAssociateFragmentDirections.
                                        actionTrackAssociateFragmentToAlbumFragment()
                                    view.findNavController().navigate(action)
                                }
                            }.show()
                    }
                }
            } else {
                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder
                    .setMessage(messageToDisplay)
                    .setTitle("Error")

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
    }

    private fun validateFields(name: String, duration: String) {
        if (name == "" || duration == "") {
            messageToDisplay = "Todos los campos son obligatorios"
            allFieldsAreValid = false
        } else {
            messageToDisplay = "Track asociado exitosamente"
            allFieldsAreValid = true
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity)
        val args: AlbumDetailFragmentArgs by navArgs()
        Log.d("Args", args.albumId.toString())
        viewModel = ViewModelProvider(this, TrackAssociateViewModel.Factory(activity.application, args.albumId)).get(
            TrackAssociateViewModel::class.java)
        viewModel.albumTrack.observe(viewLifecycleOwner) {
            it.apply {
                if (this.albumId == albumId) {
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