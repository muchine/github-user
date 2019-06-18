package com.muchine.githubuser.view

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muchine.githubuser.repository.User

class UserItemAdapter(
    private val context: Context,
    private val itemListener: UserItemView.Listener
) : RecyclerView.Adapter<UserItemViewHolder>() {

    var items: List<User> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(UserItemView(context, itemListener))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}

class UserItemViewHolder(
    itemView: UserItemView
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        val view = itemView as UserItemView
        view.bind(user)
    }

}