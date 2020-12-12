package com.exercise.motiv8ai.websocket.shared.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class GenericViewAdapter<T>(listItems: List<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listItems = mutableListOf<T>().also {
        it.addAll(listItems)
    }

    fun setItems(listItems: List<T>) {
        this.listItems.clear()
        this.listItems.addAll(listItems)
        notifyDataSetChanged()
    }

    fun addItemAtTop(item: T) {
        this.listItems.add(0, item)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false),
            viewType
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as Binder<T>).bind(getItem(position))

    fun getItem(position: Int) = listItems[position]

    override fun getItemCount() = listItems.size

    override fun getItemViewType(position: Int) = getLayoutId(position, getItem(position))

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    protected abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    internal interface Binder<T> {
        fun bind(data: T)
    }
}