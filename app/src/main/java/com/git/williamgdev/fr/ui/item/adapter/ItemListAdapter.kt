package com.git.williamgdev.fr.ui.item.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.git.williamgdev.fr.data.ItemDTO
import com.git.williamgdev.fr.data.ItemDiff
import com.git.williamgdev.fr.databinding.ItemListLayoutBinding
import com.git.williamgdev.fr.databinding.ItemListWithHeaderLayoutBinding

class ItemListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemList = mutableListOf<ItemDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ItemViewHolderType.HEADER.value -> {

                val binding =
                    ItemListWithHeaderLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ItemViewHolderHeader(binding)
            }

            ItemViewHolderType.CONTENT.value -> {
                val binding =
                    ItemListLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ItemViewHolderContent(binding)
            }
            else -> {
                throw Exception("Bad view type: $viewType")
            }
        }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolderHeader -> {
                holder.bindItem(itemList[position])
            }
            is ItemViewHolderContent -> {
                holder.bindItem(itemList[position])
            }
            else -> {
                throw Exception("Unknown view holder type: $position")
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (position > 0 && itemList[position - 1].listId == itemList[position].listId) {
            ItemViewHolderType.CONTENT.value
        } else {
            ItemViewHolderType.HEADER.value
        }

    fun submitList(newList: List<ItemDTO>) {
        val result =
            DiffUtil.calculateDiff(ItemDiff(itemList.toList(), newList), true)
        itemList.clear()
        itemList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    interface ItemViewHolder {
        fun bindItem(itemDTO: ItemDTO)
    }

    class ItemViewHolderContent(private val binding: ItemListLayoutBinding) : ItemViewHolder,
        RecyclerView.ViewHolder(binding.root) {
        override fun bindItem(itemDTO: ItemDTO) {
            binding.item = itemDTO
        }
    }

    class ItemViewHolderHeader(private val binding: ItemListWithHeaderLayoutBinding) :
        ItemViewHolder,
        RecyclerView.ViewHolder(binding.root) {
        override fun bindItem(itemDTO: ItemDTO) {
            binding.item = itemDTO
        }
    }

    enum class ItemViewHolderType(val value: Int) {
        HEADER(100), CONTENT(200)
    }
}

