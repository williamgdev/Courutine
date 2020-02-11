package com.git.williamgdev.fr.data.remote

import com.git.williamgdev.fr.data.ItemDTO
import com.git.williamgdev.fr.data.remote.jsonbin.JsonBinService
import com.git.williamgdev.fr.network.RxResponseHandler
import io.reactivex.Observable

class ItemRemoteRepositoryImpl(private val jsonBinService: JsonBinService) : ItemRemoteRepository {
    override fun getAllItems(listId: String): Observable<List<ItemDTO>> {
        return jsonBinService.getAllitems(listId)
            .flatMapObservable(RxResponseHandler())
    }

}
