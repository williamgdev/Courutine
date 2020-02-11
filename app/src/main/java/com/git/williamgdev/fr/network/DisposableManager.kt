package com.git.williamgdev.fr.network

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

object DisposableManager {
    private var compositeDisposable: CompositeDisposable? = null
    private var disposableHashMap: MutableMap<String, Disposable>? = null

    fun add(key: String, disposable: Disposable) {
        if (disposableHashMap == null) {
            disposableHashMap = WeakHashMap()
            disposableHashMap!!.put(key, disposable)

        } else {
            disposableHashMap!!.put(key, disposable)
        }

        getCompositeDisposable().add(disposable)


    }

    fun dispose(key: String) {
        if (disposableHashMap != null && disposableHashMap!![key] != null) {
            getCompositeDisposable().remove(disposableHashMap!![key]!!)
            disposableHashMap!!.remove(key)
        }

    }

    fun delete(key: String) {
        if (disposableHashMap != null && disposableHashMap!![key] != null) {
            getCompositeDisposable().delete(disposableHashMap!![key]!!)
            disposableHashMap!!.remove(key)
        }
    }

    fun disposeAll() {
        if (!getCompositeDisposable().isDisposed) {
            getCompositeDisposable().dispose()
            if (disposableHashMap != null) {
                disposableHashMap!!.clear()

            }
        }
    }

    private fun getCompositeDisposable(): CompositeDisposable {
        if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        return compositeDisposable!!
    }
}
