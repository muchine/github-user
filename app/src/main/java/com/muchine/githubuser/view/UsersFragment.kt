package com.muchine.githubuser.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.jay.widget.StickyHeadersLinearLayoutManager
import com.muchine.githubuser.R
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.repository.UserRepository
import com.muchine.githubuser.ui.base.BaseFragment
import com.muchine.githubuser.ui.core.viewmodel.getViewModel
import com.muchine.githubuser.util.Keyboard
import com.muchine.githubuser.view.adapter.UserItemAdapter
import com.muchine.githubuser.view.adapter.item.UserItemView
import com.muchine.githubuser.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.view_user_list.*

class UsersFragment : BaseFragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.viewModel = getViewModel { createUserViewModel() }
        this.adapter = UserItemAdapter(requireContext(), createItemListener())

        viewModel.users.observe(this, Observer {
            onUsersChanged(it)
        })

        initRecyclerView()
        initViewListeners()
    }

    private fun createUserViewModel(): UserViewModel {
        val repository = UserRepository(requireContext().applicationContext)
        return UserViewModel(repository)
    }

    private fun createItemListener(): UserItemView.Listener {
        return object : UserItemView.Listener {
            override fun onClickFavorite(user: User) {
                viewModel.onClickFavorite(user)
            }
        }
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

    private fun onUsersChanged(users: List<User>) {
        progressBar.visibility = View.GONE
        reload(users)
    }

    private fun onClickSearch() {
        editQuery.let {
            val query = it.text.toString()
            if (query.isEmpty()) return showToast(R.string.search_hint)

            progressBar.visibility = View.VISIBLE
            Keyboard.hide(it)
            viewModel.fetchUser(editQuery.text.toString())
        }
    }

    private fun reload(users: List<User>) {
        adapter.reload(users)
    }

}