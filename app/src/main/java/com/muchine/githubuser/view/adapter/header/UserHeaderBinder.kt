package com.muchine.githubuser.view.adapter.header

import android.content.Context
import com.muchine.githubuser.ui.core.adapter.BindableItemViewHolder
import com.muchine.githubuser.ui.core.adapter.composite.AbstractCompositeItemBinder

class UserHeaderBinder(private val context: Context) : AbstractCompositeItemBinder<UserHeader>() {

    override fun newViewHolder(): BindableItemViewHolder<UserHeader> {
        return BindableItemViewHolder(UserHeaderView(context))
    }

}