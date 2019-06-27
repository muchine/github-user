package com.muchine.githubuser.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jay.widget.StickyHeadersLinearLayoutManager
import com.muchine.githubuser.R
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.ui.base.BaseFragment
import com.muchine.githubuser.ui.core.viewmodel.getViewModel
import com.muchine.githubuser.util.Keyboard
import com.muchine.githubuser.view.adapter.PagedUserItemAdapter
import com.muchine.githubuser.view.adapter.UserItemAdapter
import com.muchine.githubuser.view.adapter.item.UserItemView
import com.muchine.githubuser.viewmodel.PagedUserViewModel
import kotlinx.android.synthetic.main.view_user_list.*

class PagedUsersFragment : BaseFragment() {

    private lateinit var viewModel: PagedUserViewModel
    private lateinit var adapter: PagedUserItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.viewModel = getViewModel { PagedUserViewModel() }
        this.adapter = PagedUserItemAdapter(requireContext(), object : UserItemView.Listener {
            override fun onClickFavorite(user: User) {

            }

            override fun onClickItem(user: User) {

            }
        })

        viewModel.users.observe(this, Observer {
            Log.d("PagedUserFragment", "user size: ${it.size}")
            onUsersChanged(it)
        })

        initRecyclerView()
        initViewListeners()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = StickyHeadersLinearLayoutManager<UserItemAdapter>(context)
        recyclerView.adapter = adapter
    }

    private fun initViewListeners() {
        iconSearch.setOnClickListener {
            onClickSearch()
        }
    }

    private fun onUsersChanged(users: PagedList<User>) {
        reload(users)
        progressBar.visibility = View.GONE
    }

    private fun onClickSearch() {
        editQuery.let {
            val query = it.text.toString()
            if (query.isEmpty()) return showSnackbar(R.string.search_hint)

            progressBar.visibility = View.VISIBLE
            Keyboard.hide(it)
            viewModel.fetchUsers(editQuery.text.toString())
        }
    }

    private fun reload(users: PagedList<User>) {
        adapter.submitList(users)
    }
}