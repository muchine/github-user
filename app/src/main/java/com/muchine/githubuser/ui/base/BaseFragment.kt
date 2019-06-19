package com.muchine.githubuser.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected fun showToast(textResId: Int, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), textResId, duration).show()
    }

}