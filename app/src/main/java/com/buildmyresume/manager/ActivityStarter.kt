package com.buildmyresume.manager


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.buildmyresume.R
import com.buildmyresume.di.activity.PerActivity
import com.buildmyresume.ui.activity.BMRBaseActivity
import com.buildmyresume.ui.fragments.BMRBaseFragment
import javax.inject.Inject


@PerActivity
class ActivityStarter @Inject
internal constructor(private val context: BMRBaseActivity) {
    private var intent: Intent? = null
    private var activity: Class<out Activity>? = null
    private var shouldAnimate = true

    fun make(activityClass: Class<out BMRBaseActivity>): ActivityBuilder {
        activity = activityClass
        intent = Intent(context, activityClass)
        return Builder()
    }

    private inner class Builder : ActivityBuilder {
        private var bundle: Bundle? = null
        private var activityOptionsBundle: Bundle? = null
        private var isToFinishCurrent: Boolean = false
        private var requestCode: Int = 0

        override fun start() {
            if (bundle != null)
                intent!!.putExtras(bundle!!)

            if (!shouldAnimate)
                intent!!.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

            if (requestCode == 0) {

                if (activityOptionsBundle == null)
                    context.startActivity(intent)
                else
                    context.startActivity(intent, activityOptionsBundle)

            } else {
                val currentFragment = context.getCurrentFragment<BMRBaseFragment>()
                if (currentFragment != null)
                    currentFragment.startActivityForResult(intent, requestCode)
                else
                    intent?.let { context.startActivityForResult(it, requestCode) }
            }

            if (shouldAnimate)
                context.overridePendingTransition(R.anim.fade_enter, R.anim.fade_exit)


            if (isToFinishCurrent)
                context.finish()
        }

        override fun addBundle(bundle: Bundle): ActivityBuilder {
            if (this.bundle != null)
                this.bundle!!.putAll(bundle)
            else
                this.bundle = bundle
            return this
        }

        override fun addSharedElements(pairs: List<Pair<View, String>>): ActivityBuilder {
            val optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(context, *pairs.toTypedArray())
            activityOptionsBundle = optionsCompat.toBundle()
            return this
        }

        override fun byFinishingCurrent(): ActivityBuilder {
            isToFinishCurrent = true
            return this
        }

        override fun byFinishingAll(): ActivityBuilder {
            intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            return this
        }


        override fun <T : BMRBaseFragment> setPage(page: Class<T>): ActivityBuilder {
            intent!!.putExtra(ACTIVITY_FIRST_PAGE, page)
            return this
        }

        override fun forResult(requestCode: Int): ActivityBuilder {
            this.requestCode = requestCode
            return this
        }

        override fun shouldAnimate(isAnimate: Boolean): ActivityBuilder {
            shouldAnimate = isAnimate
            return this
        }
    }

    companion object {
        const val ACTIVITY_FIRST_PAGE = "first_page"
    }

}
