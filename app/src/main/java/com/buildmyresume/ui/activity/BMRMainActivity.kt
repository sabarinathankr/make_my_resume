package com.buildmyresume.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import com.buildmyresume.R
import com.buildmyresume.di.activity.ActivityComponent
import com.buildmyresume.ui.fragments.SplashFragment

class BMRMainActivity : BMRBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        load(SplashFragment::class.java)
            .replace(false)
    }

    override fun findFragmentPlaceHolder(): Int {
        return R.id.mainContainer
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        recreate()
    }
}