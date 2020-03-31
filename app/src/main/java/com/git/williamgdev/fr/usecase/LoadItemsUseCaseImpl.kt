package com.git.williamgdev.fr.usecase

import com.git.williamgdev.fr.data.ItemDTO
import com.git.williamgdev.fr.data.remote.ItemRemoteRepository
import com.git.williamgdev.fr.data.repository.ItemRepository
import com.git.williamgdev.fr.network.DisposableManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.Exception

class LoadItemsUseCaseImpl(
    private val itemRepository: ItemRepository,
    private val itemRemoteRepository: ItemRemoteRepository
) : LoadItemsUseCase {

    override fun loadItems(
        listId: String,
        byPassCache: Boolean,
        onSuccess: (List<ItemDTO>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        return if (!byPassCache) {
            //@TODO Here use Database strategy
            itemRepository.getItems()
        } else {
            val tag = "getAllItems"
            val disposable = itemRemoteRepository.getAllItems(listId)
                .flatMap { itemList ->
                    Observable.just(
                        filterAndSortItems(itemList)
                    )
                }
                .doFinally { DisposableManager.delete(tag) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError)
            DisposableManager.add(tag, disposable)
        }
    }

    override suspend fun loadItems(listId: String, byPassCache: Boolean): List<ItemDTO> {
        return itemRemoteRepository.getAllItemsCall(listId).execute().body() ?: throw Exception("Error needs to be handle")
    }

    override fun filterAndSortItems(itemList: List<ItemDTO>): List<ItemDTO> {
        return itemList.filter { item ->
            item.name != null && item.name != ""
        }.sortedWith(
            compareBy<ItemDTO> { it.listId }
                .thenBy { it.name }
        )
    }
}
