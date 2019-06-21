package com.muchine.githubuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muchine.githubuser.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment(private val navigator: FragmentNavigator) : BaseFragment() {

    private lateinit var adapter: MainPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()
        adapter = MainPagerAdapter(activity, FragmentPageFactory.create(navigator), childFragmentManager)

        pager.adapter = adapter
        tabLayout.setupWithViewPager(pager)
    }

}