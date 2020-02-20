package com.git.williamgdev.fr.ui.item

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class BindingAdapters {
    companion object {
        @BindingAdapter("showOrHide")
        fun showOrHide(view: View, show: LiveData<Boolean>?) {
            show?.let {
                it.observe(view.context as LifecycleOwner, Observer { show ->
                    view.visibility = if (show) View.VISIBLE else View.GONE
                })
            }
        }
    }
}