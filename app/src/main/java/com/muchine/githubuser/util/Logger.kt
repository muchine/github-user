package com.muchine.githubuser.util

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

fun Fragment.debug(message: String) {
    Log.d(this.javaClass.simpleName, message)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}