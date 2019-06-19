package com.muchine.githubuser.view.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muchine.githubuser.view.UserItemHeaderView

class UserHeaderBinder(private val context: Context) : ItemBinder {

    private val items = HashMap<Int, UserItemHeader>()

    fun add(item: UserItemHeader, position: Int) {
        items[position] = item
    }

    override fun itemCount(): Int {
        return items.size
    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position] ?: return
        val itemHolder = holder as UserHeaderViewHolder
        itemHolder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return UserHeaderViewHolder(UserItemHeaderView(context))
    }

    override fun isBindable(position: Int): Boolean {
        return items.keys.contains(position)
    }

    override fun clear() {
        items.clear()
    }
}

class UserHeaderViewHolder(itemView: UserItemHeaderView) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: UserItemHeader) {
        val view = itemView as UserItemHeaderView
        view.bind(item)
    }

}