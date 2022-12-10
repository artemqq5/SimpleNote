package com.notekoobsiml.booketon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.notekoobsiml.booketon.R
import com.notekoobsiml.booketon.databinding.FragmentCreateCardBinding
import com.notekoobsiml.booketon.logcat
import com.notekoobsiml.booketon.repository.ViewModelRepository
import com.notekoobsiml.booketon.repository.room.Note
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateCardFragment : Fragment() {

    private lateinit var binding: FragmentCreateCardBinding
    private val viewModelRepository by lazy {
        ViewModelProvider(this)[ViewModelRepository::class.java]
    }

    private val defaultNote = Note(main_text = "", sub_text = "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelRepository.insertNote(defaultNote)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_createCardFragment_to_mainViewFragment)
        }

        binding.title.editText!!.addTextChangedListener {
            viewModelRepository.updateNote(
                Note(
                    uid = defaultNote.uid,
                    main_text = it.toString(),
                    sub_text = binding.description.editText!!.text.toString()
                )
            )

        }

        binding.description.editText!!.addTextChangedListener {
            viewModelRepository.updateNote(
                Note(
                    uid = defaultNote.uid,
                    main_text = binding.title.editText!!.text.toString(),
                    sub_text = it.toString()
                )
            )
        }

    }
}