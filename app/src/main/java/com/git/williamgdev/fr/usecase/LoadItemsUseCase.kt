package com.git.williamgdev.fr.usecase

import com.git.williamgdev.fr.data.ItemDTO

interface LoadItemsUseCase {
    fun loadItems(
        listId: String,
        byPassCache: Boolean,
        onSuccess: (List<ItemDTO>) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun filterAndSortItems(itemList: List<ItemDTO>): List<ItemDTO>
}
