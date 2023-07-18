package com.buildmyresume.di.application

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.buildmyresume.BMRApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetModule::class, ServiceModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun context(): Context

    fun provideResources(): Resources


    /*    fun provideNotificationManager(): NotificationManager*/

    fun inject(baseApplication: BMRApplication)


    @Component.Builder
    interface ApplicationComponentBuilder {

        @BindsInstance
        fun application(application: Application): ApplicationComponentBuilder

        fun build(): ApplicationComponent
    }

}
