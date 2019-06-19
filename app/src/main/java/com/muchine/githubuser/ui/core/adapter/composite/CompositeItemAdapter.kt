package com.muchine.githubuser.ui.core.adapter.composite

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class CompositeItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val binders = arrayListOf<CompositeItemBinder>()

    fun addBinder(binder: CompositeItemBinder) {
        binders.add(binder)
    }

    fun clear() {
        binders.forEach { it.clear() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binder = binders[viewType]
        return binder.createViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return binders.sumBy { it.itemCount() }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binder = findBinder(position)
        binder.bind(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        val binder = findBinder(position)
        return binders.indexOf(binder)
    }

    private fun findBinder(position: Int): CompositeItemBinder {
        return binders.find { it.isBindable(position) } ?: throw IllegalStateException("No binder found for the given position")
    }

}