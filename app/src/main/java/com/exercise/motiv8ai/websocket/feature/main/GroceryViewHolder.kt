package com.exercise.motiv8ai.websocket.feature.main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.exercise.motiv8ai.websocket.R
import com.exercise.motiv8ai.websocket.data.entity.Grocery
import com.exercise.motiv8ai.websocket.shared.adapter.GenericViewAdapter
import com.google.android.material.textview.MaterialTextView
import timber.log.Timber

class GroceryViewHolder(view: View) :
    RecyclerView.ViewHolder(view), GenericViewAdapter.Binder<Grocery> {
    private val roundBgColor: View = view.findViewById(R.id.v_bg_color)
    private val name: MaterialTextView = view.findViewById(R.id.tv_name)
    private val weight: MaterialTextView = view.findViewById(R.id.tv_weight)

    private lateinit var item: Grocery

    override fun bind(item: Grocery) {
        this.item = item
        val resources = itemView.resources
        name.text = item.name
        weight.text = item.weight
        try {
            roundBgColor.background = ColorDrawable(Color.parseColor(item.bgColor))
        } catch (e: IllegalArgumentException) {
            Timber.e(e)
        }
    }
}