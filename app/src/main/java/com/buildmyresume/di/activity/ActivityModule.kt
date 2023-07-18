package com.buildmyresume.di.activity

import com.buildmyresume.manager.FragmentHandler
import com.buildmyresume.manager.FragmentManager
import com.buildmyresume.manager.Navigator
import com.buildmyresume.ui.activity.BMRBaseActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class ActivityModule {

     @Provides
     @PerActivity
     internal fun navigator(activity: BMRBaseActivity): Navigator {
         return activity
     }
    @Provides
    @PerActivity
    internal fun fragmentManager(baseActivity: BMRBaseActivity): androidx.fragment.app.FragmentManager {
        return baseActivity.supportFragmentManager
    }

    @Provides
    @PerActivity
    @Named("placeholder")
    internal fun placeHolder(baseActivity: BMRBaseActivity): Int {
        return baseActivity.findFragmentPlaceHolder()
    }

    @Provides
    @PerActivity
    internal fun fragmentHandler(fragmentManager: FragmentManager): FragmentHandler {
        return fragmentManager
    }
}
