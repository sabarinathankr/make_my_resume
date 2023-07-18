package com.buildmyresume.manager

import com.buildmyresume.ui.fragments.BMRBaseFragment


object FragmentFactory {

    fun <T : BMRBaseFragment> getFragment(aClass: Class<T>): T? {

        try {
            return aClass.newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        return null
    }
}
