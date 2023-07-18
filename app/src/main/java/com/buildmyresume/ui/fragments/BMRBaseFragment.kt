package com.buildmyresume.ui.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.buildmyresume.di.HasComponent
import com.buildmyresume.di.activity.ActivityComponent
import com.buildmyresume.di.fragment.FragmentComponent
import com.buildmyresume.di.fragment.FragmentModule
import com.buildmyresume.manager.Navigator
import javax.inject.Inject


abstract class BMRBaseFragment : Fragment(), HasComponent<FragmentComponent> {


    @Inject
    lateinit var navigator: Navigator

    override val component: FragmentComponent
        get() {
            return getComponent(ActivityComponent::class.java).plus(FragmentModule(this))
        }

    open fun onShow() {

    }


    private fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).component)!!
    }

    protected abstract fun inject(fragmentComponent: FragmentComponent)

    override fun onAttach(context: Context) {
        inject(component)
        super.onAttach(context)
    }

}