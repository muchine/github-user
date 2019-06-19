package com.muchine.githubuser.ui.core.adapter.linear

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muchine.githubuser.ui.core.adapter.ItemBinder

class LinearItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemBinders = HashMap<Int, ItemBinder>()

    fun addBinder(binder: ItemBinder) {
        val index = itemBinders.size
        itemBinders[index] = binder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binder = itemBinders[viewType] ?: throw IllegalArgumentException("unsupported view type")
        return binder.createViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return itemBinders.values.sumBy { it.itemCount() }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        val binder = itemBinders[viewType] ?: throw IllegalArgumentException("binder not found")
        val startIndex = itemBinders.entries.filter { it.key < viewType }.sumBy { it.value.itemCount() }

        return binder.bind(holder, position - startIndex)
    }

    override fun getItemViewType(position: Int): Int {
        var index = 0
        for (key in itemBinders.keys.sorted()) {
            val binder = itemBinders[key] ?: throw IllegalArgumentException("binder not found")
            if (index <= position && position < index + binder.itemCount()) return key
            index += binder.itemCount()
        }

        throw IllegalArgumentException("position is out of item count")
    }

}