package com.muchine.githubuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainPagerAdapter(
            this,
            FragmentPageFactory.create(),
            supportFragmentManager
        )
        pager.adapter = adapter
        tabLayout.setupWithViewPager(pager)
    }
}
