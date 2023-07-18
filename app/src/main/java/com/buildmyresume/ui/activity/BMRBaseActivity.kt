package com.buildmyresume.ui.activity

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.buildmyresume.di.HasComponent
import com.buildmyresume.di.Injector
import com.buildmyresume.di.activity.ActivityComponent
import com.buildmyresume.di.activity.DaggerActivityComponent
import com.buildmyresume.manager.ActivityBuilder
import com.buildmyresume.manager.ActivityStarter
import com.buildmyresume.manager.FragmentActionPerformer
import com.buildmyresume.manager.FragmentNavigationFactory
import com.buildmyresume.manager.Navigator
import com.buildmyresume.ui.fragments.BMRBaseFragment
import javax.inject.Inject

abstract class BMRBaseActivity : AppCompatActivity(), HasComponent<ActivityComponent>, Navigator {

    override val component: ActivityComponent
        get() = activityComponent

    @Inject
    lateinit var navigationFactory: FragmentNavigationFactory

    @Inject
    lateinit var activityStarter: ActivityStarter


    private lateinit var activityComponent: ActivityComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = DaggerActivityComponent.builder()
            .bindApplicationComponent(Injector.INSTANCE.applicationComponent)
            .bindActivity(this)
            .build()
        inject(activityComponent)
        super.onCreate(savedInstanceState)
    }

    abstract fun inject(activityComponent: ActivityComponent)

    @Suppress("UNCHECKED_CAST")
    fun <F : BMRBaseFragment> getCurrentFragment(): F? {
        return if (findFragmentPlaceHolder() == 0) null else supportFragmentManager.findFragmentById(
            findFragmentPlaceHolder()
        ) as F
    }

    abstract fun findFragmentPlaceHolder(): Int



    override fun <T : BMRBaseFragment> load(tClass: Class<T>): FragmentActionPerformer<T> {
        return navigationFactory.make(tClass)
    }


    override fun loadActivity(aClass: Class<out BMRBaseActivity?>): ActivityBuilder {
        return activityStarter.make(aClass)
    }

    override fun <T : BMRBaseFragment> loadActivity(
        aClass: Class<out BMRBaseActivity>,
        pageTClass: Class<T>,
    ): ActivityBuilder {
        return activityStarter.make(aClass).setPage(pageTClass)
    }

    override fun showErrorMessage(message: String?) {}

    override fun showSuccessMessage(message: String?) {}

    override fun goBack() {

    }

    override fun popUp() {
        return navigationFactory.fragmentHandler.clearFragmentHistory()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (navigationFactory.fragmentHandler.getFragmentStackCount()>1) {
                popUp()
            }else {
                finish()
            }


        }
        return true
    }


}