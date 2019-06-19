package com.muchine.githubuser.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.jay.widget.StickyHeadersLinearLayoutManager
import com.muchine.githubuser.R
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.repository.UserRepository
import com.muchine.githubuser.ui.base.BaseFragment
import com.muchine.githubuser.util.Keyboard
import com.muchine.githubuser.viewmodels.UserViewModel
import com.muchine.githubuser.viewmodels.getViewModel
import kotlinx.android.synthetic.main.view_user_list.*

class FavoritesFragment : BaseFragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel { createUserViewModel() }
        Log.d("UserViewModel", "FavoriteFragment: ${System.identityHashCode(viewModel)}")
        adapter = UserItemAdapter(requireContext(), createItemListener())

        viewModel.favorites.observe(this, Observer {
            onFavoritesChanged(it)
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

}