package com.git.williamgdev.fr.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private var instance: Retrofit? = null

        fun getInstance(apiUrl: String, headers: Map<String, String>? = null): Retrofit {
            if (instance == null) {
                var headerInterceptor: OkHttpClient.Builder? = null
                headers?.let {
                    headerInterceptor = addHeaderInterceptor(headers)
                }
                val httpClient = headerInterceptor?: OkHttpClient.Builder()
                instance = Retrofit.Builder()
                    .baseUrl(apiUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            }
            return instance!!
        }

        private fun addHeaderInterceptor(headers: Map<String, String>): OkHttpClient.Builder? {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    var builder: Request.Builder? = null
                    chain.request()?.let { original ->
                        builder = original.newBuilder()
                        headers.forEach { itemHeader ->
                            builder?.addHeader(itemHeader.key, itemHeader.value)
                        }
                        builder?.method(original.method(), original.body())
                    }
                    builder?.let {
                        chain.proceed(it.build())
                    }
                }
        }
    }

}