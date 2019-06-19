package com.muchine.githubuser.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class CategorizedItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val binders = arrayListOf<ItemBinder>()

    fun addBinder(binder: ItemBinder) {
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
        binders.forEach { it.bind(holder, position) }
    }

    override fun getItemViewType(position: Int): Int {
        val binder = binders.find { it.isBindable(position) } ?: throw IllegalStateException("binder is not found by position")
        return binders.indexOf(binder)
    }

}