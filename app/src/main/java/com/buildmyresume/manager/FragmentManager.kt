package com.buildmyresume.manager


import android.os.Build
import android.transition.TransitionInflater
import android.view.View
import androidx.core.util.Pair
import com.buildmyresume.R
import com.buildmyresume.di.activity.PerActivity
import com.buildmyresume.ui.fragments.BMRBaseFragment
import javax.inject.Inject
import javax.inject.Named


@PerActivity
class FragmentManager @Inject
constructor(
    private val fragmentManager: androidx.fragment.app.FragmentManager,
    @param:Named("placeholder") private val placeHolder: Int,
) : FragmentHandler {


    override fun openFragment(
        baseFragment: BMRBaseFragment,
        option: FragmentHandler.Option,
        isToBackStack: Boolean,
        tag: String,
        sharedElements: List<Pair<View, String>>?,
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_enter, R.anim.fade_exit)

        // animation
        /*if (option != FragmentHandler.Option.ADD)
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, 0, 0);*/

        when (option) {

            FragmentHandler.Option.ADD -> fragmentTransaction.add(placeHolder, baseFragment, tag)
            FragmentHandler.Option.REPLACE -> {
                fragmentTransaction.replace(placeHolder, baseFragment, tag)
            }

            FragmentHandler.Option.SHOW -> if (baseFragment.isAdded)
                fragmentTransaction.show(baseFragment)

            FragmentHandler.Option.HIDE -> if (baseFragment.isAdded)
                fragmentTransaction.hide(baseFragment)
        }

        if (isToBackStack)
            fragmentTransaction.addToBackStack(tag)

        // shared element Transition
        if (!sharedElements.isNullOrEmpty()
        ) {

            val currentFragment = fragmentManager.findFragmentById(placeHolder)

            val changeTransform = TransitionInflater.from(currentFragment?.context)
                .inflateTransition(R.transition.change_image_transform)

            currentFragment?.sharedElementReturnTransition = changeTransform
            // currentFragment.setExitTransition(new Fade());

            baseFragment.sharedElementEnterTransition = changeTransform
            //baseFragment.setEnterTransition(new Fade());


            for (se: Pair<View, String> in sharedElements) {
                fragmentTransaction.addSharedElement(se.first!!, se.second!!)
            }
        }

        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun showFragment(
        fragmentToShow: BMRBaseFragment,
        vararg fragmentToHide: BMRBaseFragment,
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (fragmentToShow.isAdded) {
            fragmentTransaction.show(fragmentToShow)
            fragmentToShow.onShow()
        } else
            openFragment(fragmentToShow, FragmentHandler.Option.ADD, false, "home", null)

        for (fragment in fragmentToHide) {
            if (fragment.isAdded)
                fragmentTransaction.hide(fragment)
        }
        fragmentTransaction.commit()
    }

    override fun clearFragmentHistory(tag: String?) {
        sDisableFragmentAnimations = true

        fragmentManager.popBackStackImmediate(
            tag,
            androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        sDisableFragmentAnimations = false
    }

    override fun clearFragmentHistory() {
        fragmentManager.popBackStackImmediate()
    }

    override fun getFragmentStackCount() : Int{
     return   fragmentManager.backStackEntryCount
    }

    companion object {
        var sDisableFragmentAnimations = false
    }


}
