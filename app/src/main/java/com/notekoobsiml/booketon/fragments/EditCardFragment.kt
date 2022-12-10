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
import com.notekoobsiml.booketon.databinding.FragmentEditCardBinding
import com.notekoobsiml.booketon.repository.ViewModelRepository
import com.notekoobsiml.booketon.repository.room.Note
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCardFragment : Fragment() {

    private lateinit var binding: FragmentEditCardBinding
    private val viewModelRepository by lazy {
        ViewModelProvider(this)[ViewModelRepository::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentNote = requireArguments().getParcelable<Note>("item")!!

        binding.titleEdit.editText!!.setText(currentNote.main_text)
        binding.descriptionEdit.editText!!.setText(currentNote.sub_text)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_editCardFragment_to_mainViewFragment)
        }

        binding.titleEdit.editText!!.addTextChangedListener {
            viewModelRepository.updateNote(
                Note(
                    uid = currentNote.uid,
                    main_text = it.toString(),
                    sub_text = binding.descriptionEdit.editText!!.text.toString()
                )
            )

        }

        binding.descriptionEdit.editText!!.addTextChangedListener {
            viewModelRepository.updateNote(
                Note(
                    uid = currentNote.uid,
                    main_text = binding.titleEdit.editText!!.text.toString(),
                    sub_text = it.toString()
                )
            )
        }

    }
}