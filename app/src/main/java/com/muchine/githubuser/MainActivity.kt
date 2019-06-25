package com.muchine.githubuser

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.Slide

class MainActivity : AppCompatActivity(), FragmentNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) addFragment()
    }

    override fun pop(fragment: Fragment) {
        val manager = supportFragmentManager

        val transaction = manager.beginTransaction()
        val found = manager.findFragmentById(android.R.id.content)
//        if (found != null) transaction.hide(found)

        found?.exitTransition = Fade()
        fragment.enterTransition = Slide(Gravity.END)

        transaction
            .replace(android.R.id.content, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    private fun addFragment() {
        val fragment = MainFragment()
        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, fragment)
            .commit()
    }
}

interface FragmentNavigator {

    fun pop(fragment: Fragment)

}