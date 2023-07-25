package com.buildmyresume.util

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowInsets
import kotlin.math.roundToInt

class UiUtil {
    fun getNonNullLayoutParams(view: View): MarginLayoutParams {
        if (null == view.layoutParams) {
            view.layoutParams = MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return view.layoutParams as MarginLayoutParams
    }

    private fun getScreenWidth(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets =
                windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = activity.resources.displayMetrics
            displayMetrics.widthPixels
        }
    }

    private fun getScreenHeight(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets =
                windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars())
            windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = activity.resources.displayMetrics
            displayMetrics.heightPixels
        }
    }

    private fun ratioHeightBasedOnScreens(margin: Double, activity: Activity): Int {
        return (getScreenHeight(activity) * (margin / 812)).roundToInt()
    }

    private fun ratioWidthBasedOnScreens(margin: Double, activity: Activity): Int {
        return (getScreenWidth(activity) * (margin / 375)).roundToInt()
    }

    fun setUIAlignment(view: View, activity: Activity?, dimensionTBLRWH: Array<Double?>) {
        val param: MarginLayoutParams = getNonNullLayoutParams(view)
        if (dimensionTBLRWH[0] != null) param.topMargin = dimensionTBLRWH[0]?.let {
            ratioHeightBasedOnScreens(
                it, activity!!
            )
        }!!
        if (dimensionTBLRWH[1] != null) param.bottomMargin = dimensionTBLRWH[1]?.let {
            ratioHeightBasedOnScreens(
                it, activity!!
            )
        }!!
        if (dimensionTBLRWH[2] != null) param.leftMargin = dimensionTBLRWH[2]?.let {
            ratioWidthBasedOnScreens(
                it, activity!!
            )
        }!!
        if (dimensionTBLRWH[3] != null) param.rightMargin = dimensionTBLRWH[3]?.let {
            ratioWidthBasedOnScreens(
                it, activity!!
            )
        }!!
        if (dimensionTBLRWH[4] != null) view.layoutParams.width = dimensionTBLRWH[4]?.let {
            ratioWidthBasedOnScreens(
                it, activity!!
            )
        }!!
        if (dimensionTBLRWH[5] != null) view.layoutParams.height = dimensionTBLRWH[5]?.let {
            ratioHeightBasedOnScreens(
                it, activity!!
            )
        }!!
    }
}