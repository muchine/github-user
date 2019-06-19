package com.muchine.githubuser.ui.core.adapter

import androidx.recyclerview.widget.RecyclerView

class BindableItemViewHolder<T>(itemView: BindableItemView<T>) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: T) {
        val view = itemView as BindableItemView<T>
        view.bind(item)
    }

}