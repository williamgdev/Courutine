package com.git.williamgdev.fr

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.git.williamgdev.fr.di.itemModule
import com.git.williamgdev.fr.di.ItemRemoteModule
import org.koin.android.ext.android.startKoin

class FRApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {

            return
        }
        LeakCanary.install(this)
        startKoin(this, getModules())

    }

    private fun getModules() = listOf(itemModule, ItemRemoteModule)
}