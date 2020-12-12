package com.exercise.motiv8ai.websocket.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.exercise.motiv8ai.websocket.data.entity.Grocery
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao : BaseDao<Grocery> {

    @Query("SELECT * FROM groceries ORDER BY id DESC LIMIT 1")
    fun getLast(): Flow<Grocery>

    @Query("DELETE FROM groceries")
    fun deleteAll()

}