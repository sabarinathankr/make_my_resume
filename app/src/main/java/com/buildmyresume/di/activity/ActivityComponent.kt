package com.buildmyresume.di.activity


import com.buildmyresume.ui.activity.BMRMainActivity
import com.buildmyresume.di.application.ApplicationComponent
import com.buildmyresume.di.application.DialogFragmentModule
import com.buildmyresume.di.fragment.FragmentComponent
import com.buildmyresume.di.fragment.FragmentModule
import com.buildmyresume.manager.Navigator
import com.buildmyresume.ui.activity.BMRBaseActivity
import dagger.BindsInstance
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = [ActivityModule::class])
interface ActivityComponent {

    fun activity(): BMRBaseActivity
    fun navigator(): Navigator


    operator fun plus(fragmentModule: FragmentModule): FragmentComponent

    operator fun plus(dialogFragmentModule: DialogFragmentModule): DialogFragmentComponent

    // Inject Activities
    fun inject(mainActivity: BMRMainActivity)

    @Component.Builder
    interface Builder {

        fun bindApplicationComponent(applicationComponent: ApplicationComponent): Builder

        @BindsInstance
        fun bindActivity(baseActivity: BMRBaseActivity): Builder

        fun build(): ActivityComponent
    }
}
