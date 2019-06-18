package com.muchine.githubuser.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.muchine.githubuser.R
import com.muchine.githubuser.util.Keyboard
import com.muchine.githubuser.viewmodels.UserViewModel
import com.muchine.githubuser.viewmodels.UserViewModelFactory
import kotlinx.android.synthetic.main.view_user_list.*

class UserListFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.viewModel = ViewModelProviders.of(this, UserViewModelFactory).get(UserViewModel::class.java)
        this.adapter = UserItemAdapter(requireContext(), viewModel)

        viewModel.users.observe(this, Observer {
            onUsersChanged()
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

    private fun onUsersChanged() {
        progressBar.visibility = View.GONE
        reload()
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