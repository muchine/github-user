package com.muchine.githubuser.view

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.viewmodels.UserViewModel

class UserItemAdapter(
    private val context: Context,
    private val viewModel: UserViewModel
) : RecyclerView.Adapter<UserItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(UserItemView(context))
    }

    override fun getItemCount(): Int {
        return viewModel.getItemCount()
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val item = viewModel.getItem(position)
        holder.bind(item)
    }
}

class UserItemViewHolder(itemView: UserItemView) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        val view = itemView as UserItemView
        view.bind(user)
    }

}