package ru.batura.stat.batchat.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.batura.stat.batchat.databinding.ItemMessageBinding
import ru.batura.stat.batchat.repository.data.ChatMessage

class ChatAdapter ():
    ListAdapter<ChatMessage, ChatAdapter.ViewHolder> (TrackDiffCalback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder (val binding: ItemMessageBinding ) : RecyclerView.ViewHolder (binding.root) {

        fun bind (message: ChatMessage) {
            binding.message = message
//            binding.viewHolder = this
            binding.executePendingBindings()
        }

        fun onItemClicked () {

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMessageBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    class TrackDiffCalback : DiffUtil.ItemCallback<ChatMessage> (){

        override fun areItemsTheSame(
            oldItem: ChatMessage,
            newItem: ChatMessage
        ): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areContentsTheSame(
            oldItem: ChatMessage,
            newItem: ChatMessage
        ): Boolean {
            return  oldItem == newItem
        }
    }



}