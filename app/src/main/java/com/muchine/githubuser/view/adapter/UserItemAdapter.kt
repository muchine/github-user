package com.muchine.githubuser.view.adapter

import android.content.Context
import com.jay.widget.StickyHeaders
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.ui.core.adapter.composite.CompositeItemAdapter
import com.muchine.githubuser.view.adapter.header.UserHeader
import com.muchine.githubuser.view.adapter.header.UserHeaderBinder
import com.muchine.githubuser.view.adapter.item.UserItem
import com.muchine.githubuser.view.adapter.item.UserItemBinder
import com.muchine.githubuser.view.adapter.item.UserItemView

class UserItemAdapter(
    context: Context,
    itemListener: UserItemView.Listener
) : CompositeItemAdapter(), StickyHeaders {

    private val headerBinder = UserHeaderBinder(context)
    private val itemBinder = UserItemBinder(context, itemListener)

    init {
        addBinder(headerBinder)
        addBinder(itemBinder)
    }

    override fun isStickyHeader(position: Int): Boolean {
        return headerBinder.isBindable(position)
    }

    fun reload(users: List<User>) {
        clear()
        val grouped = users.groupBy { it.name[0] }

        var position = 0
        grouped.keys.sorted().forEach { key ->
            headerBinder.add(UserHeader(key.toString()), position++)

            val items = grouped[key]?.sortedBy { user -> user.name } ?: throw IllegalStateException("No item found")
            items.forEach { itemBinder.add(UserItem(it), position++) }
        }

        notifyDataSetChanged()
    }

}

