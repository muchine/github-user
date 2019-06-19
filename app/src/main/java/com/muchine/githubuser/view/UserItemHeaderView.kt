package com.muchine.githubuser.view

import android.content.Context
import android.view.View
import com.muchine.githubuser.R
import com.muchine.githubuser.view.adapter.UserItemHeader
import kotlinx.android.synthetic.main.view_user_item_header.view.*

class UserItemHeaderView(context: Context) : BaseView(context) {

    init {
        View.inflate(context, R.layout.view_user_item_header, this)
    }

    fun bind(header: UserItemHeader) {
        textHeader.text = header.name
    }

}