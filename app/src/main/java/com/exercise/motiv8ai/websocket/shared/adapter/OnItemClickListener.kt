package com.exercise.motiv8ai.websocket.shared.adapter

interface OnItemClickListener<T> {
        // TODO: Update argument type and name
    fun ontItemClick(item: T, position: Int)
    fun onLongItemClick(item: T?, position: Int) = Unit
}