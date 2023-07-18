package com.buildmyresume.manager

import android.view.View
import androidx.annotation.UiThread
import androidx.core.util.Pair
import com.buildmyresume.ui.fragments.BMRBaseFragment


@UiThread
interface FragmentHandler {

    /**
     * @param baseFragment   Fragment to open
     * @param option
     * @param isToBackStack
     * @param tag
     * @param sharedElements
     */
    fun openFragment(
        baseFragment: BMRBaseFragment,
        option: Option,
        isToBackStack: Boolean,
        tag: String,
        sharedElements: List<Pair<View, String>>?
    )

    /**
     * @param fragmentToShow Fragment to show
     * @param fragmentToHide array of fragments to hide
     */
    fun showFragment(fragmentToShow: BMRBaseFragment, vararg fragmentToHide: BMRBaseFragment)

    fun clearFragmentHistory(tag: String?)

    fun clearFragmentHistory()

    fun getFragmentStackCount() : Int


    enum class Option {
        ADD, REPLACE, SHOW, HIDE
    }
}