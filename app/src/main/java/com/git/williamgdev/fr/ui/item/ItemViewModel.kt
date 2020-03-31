package com.git.williamgdev.fr.ui.item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.williamgdev.fr.data.ItemDTO
import com.git.williamgdev.fr.ui.navigator.ItemNavigator
import com.git.williamgdev.fr.usecase.LoadItemsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class ItemViewModel(private val loadItemsUseCase: LoadItemsUseCase) : ViewModel() {
    val itemList = MutableLiveData<List<ItemDTO>>()
    var itemNavigator = WeakReference<ItemNavigator>(null)
    var loading = MutableLiveData<Boolean>().apply { value = false }

    fun loadItems(listId: String) {
        loading.postValue(true)
//        loadItemsUseCase.loadItems(
//            listId = listId,
//            byPassCache = true,
//            onSuccess = { items ->
//                loading.postValue(false)
//                itemList.postValue(items)
//            },
//            onError = { error ->
//                loading.postValue(false)
//                showError(error)
//            }
//        )
        CoroutineScope(Dispatchers.Default).launch {
            itemList.postValue(loadItemsUseCase.loadItems(listId, true))

            loading.postValue(false)
        }
    }

    private fun showError(throwable: Throwable?) {
        itemNavigator.get()?.showError(throwable)
    }

}
