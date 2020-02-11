package com.git.williamgdev.fr.ui.item.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.git.williamgdev.fr.data.ItemDTO
import com.git.williamgdev.fr.databinding.ItemListLayoutBinding

class ItemListAdapter : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    private val itemList = mutableListOf<ItemDTO>()

    class ItemViewHolder(private val binding: ItemListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(itemDTO: ItemDTO) {
            binding.item = itemDTO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(itemList[position])
    }

    fun submitList(newList: List<ItemDTO>) {
        val result =
            DiffUtil.calculateDiff(ItemDiff(itemList.toList(), newList), true)
        itemList.clear()
        itemList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    class ItemDiff(private val oldList: List<ItemDTO>, private val newList: List<ItemDTO>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].name == newList[newItemPosition].name &&
                    oldList[oldItemPosition].listId == newList[newItemPosition].listId
    }
}
