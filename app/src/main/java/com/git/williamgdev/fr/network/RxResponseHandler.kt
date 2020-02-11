package com.git.williamgdev.fr.network

import io.reactivex.*
import retrofit2.adapter.rxjava2.Result
import java.lang.Exception
import io.reactivex.functions.Function

class RxResponseHandler<T: Any?>: Function<Result<T>, ObservableSource<T>> {
    override fun apply(result: Result<T>): ObservableSource<T> {
        return if(result.isError) {
            Observable.error(result.error()!!)
        } else {
            if(result.response()?.isSuccessful == true) {
                result.response()?.body()?.let { response ->
                    Observable.just(response)
                }?: Observable.error(Exception("Error with request : ${result.response()?.raw()?.request()}", result.error()))
            } else {
                val message = result.response()?.message()
                Observable.error(Exception(message ?: "Error with request : ${result.response()?.raw()?.request()}", result.error()))
            }
        }
    }
}
