package com.exercise.motiv8ai.websocket.feature.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exercise.motiv8ai.websocket.R
import com.exercise.motiv8ai.websocket.data.entity.Grocery
import com.exercise.motiv8ai.websocket.shared.adapter.GenericViewAdapter

class GroceriesRecyclerViewAdapter(groceries: List<Grocery>) :
    GenericViewAdapter<Grocery>(groceries) {
    override fun getLayoutId(position: Int, obj: Grocery) = R.layout.grocery_item_list

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
        GroceryViewHolder(view)
}