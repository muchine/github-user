package com.muchine.githubuser.ui.core.adapter.composite

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muchine.githubuser.ui.core.adapter.BindableItemViewHolder

abstract class AbstractCompositeItemBinder<T> : CompositeItemBinder {

    private val items = HashMap<Int, T>()

    fun add(item: T, position: Int) {
        items[position] = item
    }

    override fun itemCount(): Int {
        return items.size
    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position] ?: return
        val itemHolder = holder as BindableItemViewHolder<T>
        itemHolder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return newViewHolder()
    }

    override fun isBindable(position: Int): Boolean {
        return items.keys.contains(position)
    }

    override fun clear() {
        items.clear()
    }

    protected abstract fun newViewHolder(): BindableItemViewHolder<T>

}