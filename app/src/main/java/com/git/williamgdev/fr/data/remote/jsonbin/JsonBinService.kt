package com.git.williamgdev.fr.data.remote.jsonbin

import com.git.williamgdev.fr.data.ItemDTO
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.adapter.rxjava2.Result

interface JsonBinService {
    @GET("/b/{itemListId}/{page}")
    fun getAllitems(@Path("itemListId") itemListId: String,
                    @Path("page") page: Int = 2
    ) :Single<Result<List<ItemDTO>>>

    @GET("/b/{itemListId}/{page}")
    fun getAllitemsCall(@Path("itemListId") itemListId: String,
                    @Path("page") page: Int = 2
    ) : Call<List<ItemDTO>>

//    @GET("/b/5e0f707f56e18149ebbebf5f/2")
//    fun getAllitems((@Header("secret-key") String secretKey) :Single<ItemsDTO>

}

