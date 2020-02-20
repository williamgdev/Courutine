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
    var loading = MutableLiveData<Boolean>().apply { value = false }

    fun loadItems(listId: String) {
        loading.postValue(true)
        loadItemsUseCase.loadItems(
            listId = listId,
            byPassCache = true,
            onSuccess = { items ->
                loading.postValue(false)
                itemList.postValue(items)
            },
            onError = { error ->
                loading.postValue(false)
                showError(error)
            }
        )
    }

    private fun showError(throwable: Throwable?) {
        itemNavigator.get()?.showError(throwable)
    }

}
