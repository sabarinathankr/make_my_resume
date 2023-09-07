package com.buildmyresume.di.fragment

import com.buildmyresume.ui.fragments.FeatureDetailsFragment
import com.buildmyresume.ui.fragments.GetPersonalDetailsFragment
import com.buildmyresume.ui.fragments.HomeFragment
import com.buildmyresume.ui.fragments.ObjectiveFragment
import com.buildmyresume.ui.fragments.SplashFragment
import com.buildmyresume.ui.fragments.ViewPersonalDetailsFragment
import com.buildmyresume.ui.fragments.WorkExperienceFragment
import dagger.Subcomponent


@PerFragment
@Subcomponent(modules = [(FragmentModule::class)])
interface FragmentComponent {
    fun inject(splashFragment: SplashFragment)
    fun inject(homeFragment: HomeFragment)

    fun inject(getPersonalDetailsFragment: GetPersonalDetailsFragment)
    fun inject(viewPersonalDetailsFragment: ViewPersonalDetailsFragment)
    fun inject(featureDetailsFragment: FeatureDetailsFragment)
    fun inject(objectiveFragment: ObjectiveFragment)
    fun inject(workExperienceFragment: WorkExperienceFragment)
}
