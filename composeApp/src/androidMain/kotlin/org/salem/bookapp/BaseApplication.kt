package org.salem.bookapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.salem.bookapp.setupDI.initKoin

//import io.github.aakira.napier.DebugAntilog
//import io.github.aakira.napier.Napier

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin{
            androidContext(this@BaseApplication)
        }



//        initLogger()
    }

//    private fun initLogger() {
//        Napier.base(DebugAntilog())
//    }

}