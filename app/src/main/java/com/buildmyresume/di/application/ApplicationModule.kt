package com.buildmyresume.di.application

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    internal fun provideResources(application: Application): Resources {
        return application.resources
    }


    @Provides
    @Singleton
    internal fun provideApplicationContext(application: Application): Context {
        return application
    }
}
