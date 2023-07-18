package com.buildmyresume.di

import android.app.Application
import com.buildmyresume.di.application.ApplicationComponent
import com.buildmyresume.di.application.DaggerApplicationComponent

enum class Injector() {
    INSTANCE;

    lateinit var applicationComponent: ApplicationComponent
        internal set

    fun initAppComponent(application: Application) {
        applicationComponent = DaggerApplicationComponent.builder()
            .application(application)
            .build()
    }
}