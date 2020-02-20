package com.git.williamgdev.fr.data

import androidx.recyclerview.widget.DiffUtil


class ItemDiff(private val oldList: List<ItemDTO>, private val newList: List<ItemDTO>) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].name == newList[newItemPosition].name &&
                oldList[oldItemPosition].listId == newList[newItemPosition].listId
}