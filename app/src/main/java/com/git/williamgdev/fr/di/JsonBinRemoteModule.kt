package com.git.williamgdev.fr.di

import com.git.williamgdev.fr.R
import com.git.williamgdev.fr.data.remote.jsonbin.JsonBinService
import com.git.williamgdev.fr.network.ApiContext
import com.git.williamgdev.fr.network.RetrofitClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val ItemRemoteModule = module {
    single {
        RetrofitClient.getInstance(
            apiUrl = androidApplication().getString(R.string.fr_api_url),
            headers = HashMap<String, String>().apply {
                put("secret-key", androidApplication().getString(R.string.fr_api_key))
            }
        )
    }
   single { ApiContext<JsonBinService>(get())}

}
