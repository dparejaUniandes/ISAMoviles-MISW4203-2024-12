package com.example.vinilosapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
            viewModel.refreshDataFromNetwork(postParams)

            Thread.sleep(2000)
            val action = AlbumCreateFragmentDirections.actionAlbumCreateFragmentToAlbumFragment()
            // Navigate using that action
            view.findNavController().navigate(action)
        }
    }
}