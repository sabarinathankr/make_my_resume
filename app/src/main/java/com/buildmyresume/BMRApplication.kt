package com.buildmyresume

import android.app.Application
import com.buildmyresume.di.Injector

class BMRApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.INSTANCE.initAppComponent(this)
        Injector.INSTANCE.applicationComponent.inject(this)
    }
}