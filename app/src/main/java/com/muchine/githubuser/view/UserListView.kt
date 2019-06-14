package com.muchine.githubuser.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.muchine.githubuser.R
import com.muchine.githubuser.util.Keyboard
import com.muchine.githubuser.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.view_user_list.view.*

class UserListView : BaseView {

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserItemAdapter

    constructor(context: Context): super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    init {
        View.inflate(context, R.layout.view_user_list, this)
    }

    fun initialize(owner: LifecycleOwner, viewModel: UserViewModel) {
        this.viewModel = viewModel
        this.adapter = UserItemAdapter(context, viewModel)

        viewModel.users.observe(owner, Observer {
            progressBar.visibility = View.GONE
            reload()
        })

        initRecyclerView()
        initViewListeners()

        reload()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun initViewListeners() {
        iconSearch.setOnClickListener {
            onClickSearch()
        }
    }

    private fun onClickSearch() {
        progressBar.visibility = View.VISIBLE
        editQuery.let {
            Keyboard.hide(it)
            viewModel.fetch(editQuery.text.toString())
        }
    }

    private fun reload() {
        adapter.notifyDataSetChanged()
    }

}