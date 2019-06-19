package com.muchine.githubuser.view.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muchine.githubuser.view.UserItemView

class UserItemBinder(
    private val context: Context,
    private val itemListener: UserItemView.Listener
) : ItemBinder {

    private val items = HashMap<Int, UserItem>()

    fun add(item: UserItem, position: Int) {
        items[position] = item
    }

    override fun itemCount(): Int {
        return items.size
    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position] ?: return
        val itemHolder = holder as UserItemViewHolder
        itemHolder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return UserItemViewHolder(UserItemView(context, itemListener))
    }

    override fun isBindable(position: Int): Boolean {
        return items.keys.contains(position)
    }

    override fun clear() {
        items.clear()
    }

}

class UserItemViewHolder(itemView: UserItemView) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: UserItem) {
        val view = itemView as UserItemView
        view.bind(item.user)
    }

}