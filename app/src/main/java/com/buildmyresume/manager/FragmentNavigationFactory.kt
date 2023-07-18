package com.buildmyresume.manager

import android.os.Bundle
import android.view.View
import androidx.core.util.Pair
import com.buildmyresume.di.activity.PerActivity
import com.buildmyresume.ui.fragments.BMRBaseFragment
import javax.inject.Inject

@PerActivity
class FragmentNavigationFactory @Inject
constructor(val fragmentHandler: FragmentHandler) {

    private var fragment: BMRBaseFragment? = null
    private var tag: String? = null

    fun <T : BMRBaseFragment> make(aClass: Class<T>): FragmentActionPerformer<T> {
        return make(FragmentFactory.getFragment(aClass))
    }

    fun <T : BMRBaseFragment> make(fragment: T?): FragmentActionPerformer<T> {
        this.fragment = fragment
        this.tag = fragment!!.javaClass.simpleName
        return Provider(fragment, this)
    }

    private inner class Provider<T : BMRBaseFragment>
        (private val fragment: T, private val navigationFactory: FragmentNavigationFactory) :
        FragmentActionPerformer<T> {
        var sharedElements: List<Pair<View, String>>? = null

        override fun add(toBackStack: Boolean) {
            navigationFactory.fragmentHandler.openFragment(
                fragment,
                FragmentHandler.Option.ADD, toBackStack, tag!!, sharedElements
            )
        }

        override fun add(toBackStack: Boolean, tag: String) {
            navigationFactory.fragmentHandler.openFragment(
                fragment,
                FragmentHandler.Option.ADD, toBackStack, tag, sharedElements
            )
        }

        override fun replace(toBackStack: Boolean) {
            navigationFactory.fragmentHandler.openFragment(
                fragment,
                FragmentHandler.Option.REPLACE, toBackStack, tag!!, sharedElements
            )
        }

        override fun replace(toBackStack: Boolean, tag: String) {
            navigationFactory.fragmentHandler.openFragment(
                fragment,
                FragmentHandler.Option.REPLACE, toBackStack, tag, sharedElements
            )
        }


        override fun setBundle(bundle: Bundle): FragmentActionPerformer<*> {
            fragment.arguments = bundle
            return this
        }

        override fun addSharedElements(sharedElements: List<Pair<View, String>>): FragmentActionPerformer<*> {
            this.sharedElements = sharedElements
            return this
        }

        override fun clearHistory(tag: String?): FragmentActionPerformer<*> {
            navigationFactory.fragmentHandler.clearFragmentHistory(tag)
            return this
        }

        override fun clearHistory(): FragmentActionPerformer<*> {
            navigationFactory.fragmentHandler.clearFragmentHistory()
            return this
        }

        override fun hasData(passable: Passable<T>): FragmentActionPerformer<*> {
            passable.passData(fragment)

            return this
        }

        override fun getFragmentStackCount() :Int{
           return navigationFactory.fragmentHandler.getFragmentStackCount()
        }



    }
}