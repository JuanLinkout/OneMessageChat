package com.example.onemessagechat.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue

object StatusBarUtil {
    @SuppressLint("InternalInsetResource")
    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else {
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 24f, context.resources.displayMetrics
            ).toInt()
        }
    }
}