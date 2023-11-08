package com.example.aplikasipetshop.utils

import android.content.Context
import android.util.TypedValue

object UtilsContext {
    fun Context.dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
    }

    fun Context.getScreenWidth(): Float {
        val displayMetrics = resources.displayMetrics
        return (displayMetrics.widthPixels / displayMetrics.density)
    }

    fun Context.getScreenHeight(): Float {
        val displayMetrics = resources.displayMetrics
        return (displayMetrics.heightPixels / displayMetrics.density)
    }

}