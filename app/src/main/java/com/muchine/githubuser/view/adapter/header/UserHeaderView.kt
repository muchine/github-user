package com.muchine.githubuser.view.adapter.header

import android.content.Context
import android.view.View
import com.muchine.githubuser.R
import com.muchine.githubuser.ui.core.adapter.BindableItemView
import kotlinx.android.synthetic.main.view_user_item_header.view.*

class UserHeaderView(context: Context) : BindableItemView<UserHeader>(context) {

    init {
        View.inflate(context, R.layout.view_user_item_header, this)
    }

    override fun bind(header: UserHeader) {
        textHeader.text = header.name
    }

}