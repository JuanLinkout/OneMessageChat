package com.example.onemessagechat.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onemessagechat.databinding.ChatItemBinding
import com.example.onemessagechat.domain.entities.chats.Chat

class ChatItemAdapter(
    private val dataset: Array<Chat>, private val onClickListener: AdapterOnClickListener
) : RecyclerView.Adapter<ChatItemAdapter.ChatItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ChatItemAdapter.ChatItemViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ChatItemAdapter.ChatItemViewHolder, position: Int
    ) {
        holder.bind(dataset[position], position)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class ChatItemViewHolder(private val binding: ChatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(chat: Chat, position: Int) {
            binding.chatInitialText.text = chat.key[0].toString()
            binding.chatKeyText.text = chat.key
            binding.chatMessageText.text = chat.message
            binding.root.setOnClickListener { onClickListener.onItemClick(position) }
        }
    }
}