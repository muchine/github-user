package com.muchine.githubuser.view

import android.content.Context
import android.view.View
import com.muchine.githubuser.R
import com.muchine.githubuser.core.image.option.ImageOptionBuilder
import com.muchine.githubuser.repository.User
import kotlinx.android.synthetic.main.view_user_item.view.*
import kr.co.rememberapp.ui.photo.image.SimpleImage

class UserItemView : BaseView {

    constructor(context: Context): super(context)

    init {
        View.inflate(context, R.layout.view_user_item, this)
    }

    fun bind(user: User) {
        userName.text = user.name
        renderImage(user)
    }

    private fun renderImage(user: User) {
        val option = ImageOptionBuilder()
            .setRounded(true)
            .build()

        SimpleImage(user.imageUrl, option).render(userImage)
    }

}