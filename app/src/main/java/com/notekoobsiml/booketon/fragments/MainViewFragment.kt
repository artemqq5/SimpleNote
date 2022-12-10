package com.notekoobsiml.booketon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.notekoobsiml.booketon.R
import com.notekoobsiml.booketon.databinding.FragmentMainViewBinding
import com.notekoobsiml.booketon.logcat
import com.notekoobsiml.booketon.recyclerViewAdapter.RecyclerAdapter
import com.notekoobsiml.booketon.repository.ViewModelRepository
import com.notekoobsiml.booketon.repository.room.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainViewFragment : Fragment() {

    private lateinit var binding: FragmentMainViewBinding
    private val viewModelRepository by lazy {
        ViewModelProvider(this)[ViewModelRepository::class.java]
    }

    private val adapterRecyclerView by lazy {
        RecyclerAdapter(clickListenerItemOfRecycler)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myRecyclerView.adapter = adapterRecyclerView

        lifecycleScope.launch(Dispatchers.IO) {
            viewModelRepository.allNoteDataList.collectLatest { listOfAllNotes ->
                withContext(Dispatchers.Main) {
                    adapterRecyclerView.changeList(listOfAllNotes)
                    logcat(listOfAllNotes)
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainViewFragment_to_createCardFragment)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModelRepository.deleteNote(
                    adapterRecyclerView.listAdapter[viewHolder.adapterPosition]
                )
            }

        }).attachToRecyclerView(binding.myRecyclerView)


    }

    private val clickListenerItemOfRecycler: (Note) -> Unit = {
        val bundle = bundleOf("item" to it)
        findNavController().navigate(R.id.action_mainViewFragment_to_editCardFragment, bundle)
    }

}