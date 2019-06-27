package com.muchine.githubuser.view.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.ui.core.adapter.BindableItemViewHolder
import com.muchine.githubuser.view.adapter.item.UserItem
import com.muchine.githubuser.view.adapter.item.UserItemView

class PagedUserItemAdapter(
    private val context: Context,
    private val itemListener: UserItemView.Listener
) : PagedListAdapter<User, BindableItemViewHolder<UserItem>>(UserDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableItemViewHolder<UserItem> {
        return BindableItemViewHolder(UserItemView(context, itemListener))
    }

    override fun onBindViewHolder(holder: BindableItemViewHolder<UserItem>, position: Int) {
        val item = getItem(position) ?: throw IllegalStateException("Item not found")
        holder.bind(UserItem(item))
    }
}

object UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}