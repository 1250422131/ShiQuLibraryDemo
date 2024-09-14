package com.imcys.shiqulibrarydemo.base

import android.app.Application
import com.imcys.shiqulibrarydemo.di.appModule
import com.kongzue.dialogx.DialogX
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DialogX.init(this)
        startKoin {
            androidContext(this@App)
            // Load modules
            modules(appModule)
        }
    }
}