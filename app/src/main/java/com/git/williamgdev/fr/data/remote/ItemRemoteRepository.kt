package com.git.williamgdev.fr.data.remote

import com.git.williamgdev.fr.data.ItemDTO
import io.reactivex.Observable

interface ItemRemoteRepository {
    fun getAllItems(listId: String): Observable<List<ItemDTO>>
}
