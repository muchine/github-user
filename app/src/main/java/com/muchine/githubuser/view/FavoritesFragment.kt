package com.muchine.githubuser.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.jay.widget.StickyHeadersLinearLayoutManager
import com.muchine.githubuser.FragmentNavigator
import com.muchine.githubuser.R
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.repository.UserRepository
import com.muchine.githubuser.ui.base.BaseFragment
import com.muchine.githubuser.ui.comment.CommentsFragment
import com.muchine.githubuser.ui.core.viewmodel.getViewModel
import com.muchine.githubuser.util.Keyboard
import com.muchine.githubuser.view.adapter.UserItemAdapter
import com.muchine.githubuser.view.adapter.item.UserItemView
import com.muchine.githubuser.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.view_user_list.*

class FavoritesFragment(private val navigator: FragmentNavigator) : BaseFragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel { createUserViewModel() }
        adapter = UserItemAdapter(requireContext(), createItemListener())

        viewModel.favorites.observe(this, Observer {
            onFavoritesChanged(it)
        })

        initRecyclerView()
        initViewListeners()

        onClickSearch()
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

            override fun onClickItem(user: User) {
                popFragment()
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

    private fun onFavoritesChanged(favorites: List<User>) {
        progressBar.visibility = View.GONE
        reload(favorites)
    }

    private fun onClickSearch() {
        progressBar.visibility = View.VISIBLE
        editQuery.let {
            Keyboard.hide(it)
            viewModel.fetchFavorite(editQuery.text.toString())
        }
    }

    private fun reload(favorites: List<User>) {
        adapter.reload(favorites)
    }

    private fun popFragment() {
        navigator.pop(CommentsFragment())
    }

}