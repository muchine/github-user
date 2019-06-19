package com.muchine.githubuser.ui.core.adapter

import android.content.Context
import com.muchine.githubuser.ui.base.BaseView

abstract class BindableItemView<T>(context: Context) : BaseView(context) {

    abstract fun bind(item: T)

}