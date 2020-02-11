package com.git.williamgdev.fr.ui.item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.git.williamgdev.fr.data.ItemDTO
import com.git.williamgdev.fr.ui.navigator.ItemNavigator
import com.git.williamgdev.fr.usecase.LoadItemsUseCase
import java.lang.ref.WeakReference

class ItemViewModel(private val loadItemsUseCase: LoadItemsUseCase) : ViewModel() {
    val itemList = MutableLiveData<List<ItemDTO>>()
    var itemNavigator = WeakReference<ItemNavigator>(null)

    fun loadItems(listId: String) {
        //@TODO Progress Bar
        loadItemsUseCase.loadItems(
            listId = listId,
            byPassCache = true,
            onSuccess = { items ->
                itemList.postValue(items)
            },
            onError = { error ->
                showError(error)
            }
        )
    }

    private fun showError(throwable: Throwable?) {
        itemNavigator.get()?.showError(throwable)
    }

    private fun displayItems(itemsDTO: List<ItemDTO>?) {
        itemNavigator.get()?.displayItems(itemsDTO)
    }

}
