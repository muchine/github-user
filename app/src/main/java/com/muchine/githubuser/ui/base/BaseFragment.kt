package com.muchine.githubuser.ui.base

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected fun showToast(textResId: Int, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), textResId, duration).show()
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