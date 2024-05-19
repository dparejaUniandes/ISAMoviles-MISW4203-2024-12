package com.example.vinilosapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.vinilosapp.R
import com.example.vinilosapp.viewmodels.AlbumCreateViewModel
import com.google.android.material.textfield.TextInputEditText


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumCreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumCreateFragment : Fragment() {
    private lateinit var viewModel: AlbumCreateViewModel
    private var messageToDisplay : String = ""
    private val possiblesGenres = arrayOf("Classical", "Salsa", "Rock", "Folk")
    private val possiblesRecordLabel = arrayOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")
    private var allFieldsAreValid : Boolean = true
    // yyyy-mm-dd
    val dateRegex =
        "([0-9]{4})[\\-](1[0-2]|0[1-9]|[1-9])[\\-](3[01]|[12][0-9]|0[1-9]|[1-9])$".toRegex()
    var counterDialogPressButton : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.album_create_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val createButton: Button = requireView().findViewById(R.id.create_button)
        val activity = requireNotNull(this.activity)
        viewModel = ViewModelProvider(this, AlbumCreateViewModel.Factory(activity.application)).get(
            AlbumCreateViewModel::class.java)
        createButton.setOnClickListener {
            Log.d("Button", "pressed")
            val nameTxt : TextInputEditText = view.findViewById(R.id.txt_post_name)
            val coverTxt : TextInputEditText = view.findViewById(R.id.txt_post_cover_url)
            val dateTxt : TextInputEditText = view.findViewById(R.id.txt_post_date)
            val descriptionTxt : TextInputEditText = view.findViewById(R.id.txt_post_description)
            val genreTxt : TextInputEditText = view.findViewById(R.id.txt_post_genre)
            val recordLabelTxt : TextInputEditText = view.findViewById(R.id.txt_post_album_create_record_label)
            val postParams = mapOf<String, String>(
                "name" to nameTxt.text.toString(),
                "cover" to coverTxt.text.toString(),
                "releaseDate" to dateTxt.text.toString(),
                "description" to descriptionTxt.text.toString(),
                "genre" to genreTxt.text.toString(),
                "recordLabel" to recordLabelTxt.text.toString()
            )

            validateFields(postParams)
            Log.d("Message", messageToDisplay)
            if (allFieldsAreValid) {
                viewModel.refreshDataFromNetwork(postParams)
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
                                        AlbumCreateFragmentDirections.actionAlbumCreateFragmentToAlbumFragment()
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

    private fun validateFields(postParams : Map<String, String>) {
        if (postParams["name"] == "" || postParams["cover"] == "" || postParams["releaseDate"] == ""
            || postParams["description"] == "" || postParams["genre"] == "" || postParams["recordLabel"] == "") {
            messageToDisplay = "Todos los campos son obligatorios"
            allFieldsAreValid = false
        } else if (!possiblesGenres.contains(postParams["genre"])) {
            messageToDisplay = "Género no permitido, deben ser: [Classical, Salsa, Rock, Folk]"
            allFieldsAreValid = false
        } else if (!possiblesRecordLabel.contains(postParams["recordLabel"])) {
            messageToDisplay = "Discografía no permitida, deben ser: [Sony Music, EMI, Discos Fuentes, Elektra, Fania Records]"
            allFieldsAreValid = false
        } else if (!dateRegex.containsMatchIn(postParams["releaseDate"]!!)) {
            messageToDisplay = "Fecha no permitida, debe tener un formato yyyy-mm-dd"
            allFieldsAreValid = false
        } else {
            messageToDisplay = "Álbum creado exitosamente"
            allFieldsAreValid = true
        }

    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}