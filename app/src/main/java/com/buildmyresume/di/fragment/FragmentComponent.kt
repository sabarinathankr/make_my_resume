package com.buildmyresume.di.fragment

import com.buildmyresume.ui.fragments.HomeFragment
import com.buildmyresume.ui.fragments.SplashFragment
import dagger.Subcomponent


@PerFragment
@Subcomponent(modules = [(FragmentModule::class)])
interface FragmentComponent {
    fun inject(splashFragment: SplashFragment)
    fun inject(homeFragment: HomeFragment)
}
