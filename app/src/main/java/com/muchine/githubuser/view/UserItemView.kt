package com.muchine.githubuser.view

import android.content.Context
import android.view.View
import com.muchine.githubuser.R
import com.muchine.githubuser.core.image.option.ImageOptionBuilder
import com.muchine.githubuser.repository.User
import kotlinx.android.synthetic.main.view_user_item.view.*
import kr.co.rememberapp.ui.photo.image.SimpleImage

class UserItemView(
    context: Context,
    private val itemListener: Listener
) : BaseView(context) {

    init {
        View.inflate(context, R.layout.view_user_item, this)
    }

    fun bind(user: User) {
        userName.text = user.name
        renderImage(user)
        renderFavorite(user)
    }

    private fun renderImage(user: User) {
        val option = ImageOptionBuilder()
            .setRounded(true)
            .build()

        SimpleImage(user.imageUrl, option).render(userImage)
    }

    private fun renderFavorite(user: User) {
        val imageId = if (user.isFavorite) R.drawable.ic_favorite_black_24dp else R.drawable.ic_favorite_border_black_24dp
        iconFavorite.setImageDrawable(context.resources.getDrawable(imageId, null))
        iconFavorite.setOnClickListener {
            itemListener.onClickFavorite(user)
        }
    }

    interface Listener {

        fun onClickFavorite(user: User)

    }

}