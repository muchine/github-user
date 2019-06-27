package com.muchine.githubuser

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.muchine.githubuser.view.FavoritesFragment
import com.muchine.githubuser.view.PagedUsersFragment
import com.muchine.githubuser.view.UsersFragment

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

    fun create(): List<FragmentPage> {
        return arrayListOf(
            FragmentPage(R.string.tab_paged, PagedUsersFragment()),
            FragmentPage(R.string.tab_users, UsersFragment()),
            FragmentPage(R.string.tab_favorites, FavoritesFragment())
        )
    }

}