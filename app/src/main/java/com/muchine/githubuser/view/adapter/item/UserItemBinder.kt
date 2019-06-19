package com.muchine.githubuser.view.adapter.item

import android.content.Context
import com.muchine.githubuser.ui.core.adapter.BindableItemViewHolder
import com.muchine.githubuser.ui.core.adapter.composite.AbstractCompositeItemBinder

class UserItemBinder(
    private val context: Context,
    private val itemListener: UserItemView.Listener
) : AbstractCompositeItemBinder<UserItem>() {

    override fun newViewHolder(): BindableItemViewHolder<UserItem> {
        return BindableItemViewHolder(UserItemView(context, itemListener))
    }

}
