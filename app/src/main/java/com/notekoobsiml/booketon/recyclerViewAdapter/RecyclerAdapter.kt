package com.notekoobsiml.booketon.recyclerViewAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.notekoobsiml.booketon.R
import com.notekoobsiml.booketon.databinding.ItemRecyclerViewBinding
import com.notekoobsiml.booketon.repository.room.Note

class RecyclerAdapter(
    private val onClickListener: (Note) -> Unit
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var listAdapter: List<Note> = emptyList()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRecyclerViewBinding.bind(view)

        fun bind(item: Note) {
            binding.mainText.text = item.main_text
            binding.subText.text = item.sub_text

            binding.root.setOnClickListener {
                onClickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_recycler_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listAdapter[position])
    }

    override fun getItemCount() = listAdapter.size

    @SuppressLint("NotifyDataSetChanged")
    fun changeList(newList: List<Note>) {
        this.listAdapter = newList.reversed()
        notifyDataSetChanged()
    }
}