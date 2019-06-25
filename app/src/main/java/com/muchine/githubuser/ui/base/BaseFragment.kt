package com.muchine.githubuser.ui.base

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.muchine.githubuser.FragmentNavigator

abstract class BaseFragment : Fragment() {

    protected lateinit var navigator: FragmentNavigator

    protected fun showSnackbar(textResId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
        view?.let { Snackbar.make(it, textResId, duration).show() }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FragmentNavigator) this.navigator = context
    }

    override fun onStop() {
        super.onStop()
        Log.d(this.javaClass.simpleName, "Fragment stopped.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(this.javaClass.simpleName, "Fragment resumed.")
    }
}