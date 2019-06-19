package com.muchine.githubuser.ui.core.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ItemBinder {

    fun itemCount(): Int

    fun bind(holder: RecyclerView.ViewHolder, position: Int)

    fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

}