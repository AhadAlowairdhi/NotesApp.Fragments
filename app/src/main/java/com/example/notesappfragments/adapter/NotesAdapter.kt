package com.example.notesappfragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappfragments.DB.Notes
import com.example.notesappfragments.HomeFragment
import com.example.notesappfragments.databinding.NoteRowBinding


class NotesAdapter(val Fragment: HomeFragment,var notesList: List<Notes>): RecyclerView.Adapter<NotesAdapter.ItemViewHolder>() {
    class ItemViewHolder(var binding: NoteRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            NoteRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val aNote = notesList[position]

        holder.binding.apply {
            tvNote.text = aNote.note

            edtNote.setOnClickListener{
                with(Fragment.sharedPreferences.edit()){
                    putInt("NoteID",aNote.id)
                    apply()
                }
                Fragment.UpdateGo()
            }

            delNote.setOnClickListener{
                Fragment.DialogDel(aNote)
            }
        }
    }

    override fun getItemCount(): Int = notesList.size

    fun rvChange(notes: List<Notes>) {
        this.notesList = notes
        notifyDataSetChanged()
    }

}