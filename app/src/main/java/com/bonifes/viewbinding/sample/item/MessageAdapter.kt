package com.bonifes.viewbinding.sample.item

import androidx.recyclerview.widget.DiffUtil
import com.bonifes.viewbinding.base.SimpleListAdapter
import com.bonifes.viewbinding.sample.databinding.ItemMessageBinding

data class Message(
    val id: String,
    val content: String
) {
    class DiffCallback : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Message, newItem: Message) = oldItem == newItem
    }
}

class MessageAdapter : SimpleListAdapter<Message, ItemMessageBinding>(Message.DiffCallback()) {

    override fun onBindViewHolder(binding: ItemMessageBinding, item: Message, position: Int) {
        binding.tvContent.text = item.content
    }
}