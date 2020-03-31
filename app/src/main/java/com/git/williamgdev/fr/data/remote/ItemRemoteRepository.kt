package com.git.williamgdev.fr.data.remote

import com.git.williamgdev.fr.data.ItemDTO
import io.reactivex.Observable
import retrofit2.Call

interface ItemRemoteRepository {
    fun getAllItems(listId: String): Observable<List<ItemDTO>>
    fun getAllItemsCall(listId: String): Call<List<ItemDTO>>
}
