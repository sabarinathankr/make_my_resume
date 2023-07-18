package com.buildmyresume.di.fragment

import com.buildmyresume.ui.fragments.BMRBaseFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val baseFragment: BMRBaseFragment) {

    @Provides
    @PerFragment
    internal fun provideBaseFragment(): BMRBaseFragment {
        return baseFragment
    }

}
