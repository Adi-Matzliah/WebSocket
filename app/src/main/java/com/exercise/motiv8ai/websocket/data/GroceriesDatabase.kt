package com.exercise.motiv8ai.websocket.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exercise.motiv8ai.websocket.data.dao.GroceryDao
import com.exercise.motiv8ai.websocket.data.entity.Grocery

@Database(
    entities = [Grocery::class],
    version = 1,
    exportSchema = false
)
abstract class GroceriesDatabase : RoomDatabase() {
    abstract fun groceryDao(): GroceryDao
}