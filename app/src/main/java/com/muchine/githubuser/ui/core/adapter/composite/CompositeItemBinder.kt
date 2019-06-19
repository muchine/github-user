package com.muchine.githubuser.ui.core.adapter.composite

import com.muchine.githubuser.ui.core.adapter.ItemBinder

interface CompositeItemBinder : ItemBinder {

    fun isBindable(position: Int): Boolean

    fun clear()

}