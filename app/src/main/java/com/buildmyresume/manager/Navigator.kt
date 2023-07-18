package com.buildmyresume.manager

import com.buildmyresume.ui.activity.BMRBaseActivity
import com.buildmyresume.ui.fragments.BMRBaseFragment


interface Navigator {

    fun <T : BMRBaseFragment> load(tClass: Class<T>): FragmentActionPerformer<T>

    fun loadActivity(aClass: Class<out BMRBaseActivity>): ActivityBuilder

    fun <T : BMRBaseFragment> loadActivity(
        aClass: Class<out BMRBaseActivity>,
        pageTClass: Class<T>
    ): ActivityBuilder

    fun showErrorMessage(message: String?)

    fun showSuccessMessage(message: String?)

    fun goBack()

    fun popUp()

    fun finish()

}
