package com.muchine.githubuser.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.muchine.githubuser.R

class MainPagerAdapter(
    private val context: Context,
    private val pages: List<FragmentPage>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return pages[position].fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(pages[position].titleResId)
    }

}

class FragmentPage(
    val titleResId: Int,
    val fragment: Fragment
)

object FragmentPageFactory {

    private val pages = arrayListOf(
        FragmentPage(R.string.tab_api, UserListFragment()),
        FragmentPage(R.string.tab_local, FavoritesFragment())
    )

    fun create() = pages

}