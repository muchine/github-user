package com.muchine.githubuser.view.adapter.item

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.muchine.githubuser.R
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.ui.core.adapter.BindableItemView
import com.muchine.githubuser.ui.core.image.option.ImageOptionBuilder
import kotlinx.android.synthetic.main.view_user_item.view.*
import kr.co.rememberapp.ui.photo.image.SimpleImage

class UserItemView(
    context: Context,
    private val itemListener: Listener
) : BindableItemView<UserItem>(context) {

    init {
        View.inflate(context, R.layout.view_user_item, this)
    }

    override fun bind(item: UserItem) {
        val user = item.user

        renderUserName(user)
        renderImage(user)
        renderFavorite(user)
    }

    private fun renderUserName(user: User) {
        userName.text = user.name
        userName.setOnClickListener {
            itemListener.onClickItem(user)
        }
    }

    private fun renderImage(user: User) {
        val option = ImageOptionBuilder()
            .setRounded(true)
            .build()

        SimpleImage(user.imageUrl, option).render(userImage)
    }

    private fun renderFavorite(user: User) {
        val imageId = if (user.isFavorite) R.drawable.ic_favorite_black_24dp else R.drawable.ic_favorite_border_black_24dp
        iconFavorite.setImageDrawable(ContextCompat.getDrawable(context, imageId))
        iconFavorite.setOnClickListener {
            itemListener.onClickFavorite(user)
        }
    }

    interface Listener {

        fun onClickFavorite(user: User)

        fun onClickItem(user: User)

    }

}