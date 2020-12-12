package com.exercise.motiv8ai.websocket.util.di.module

import android.content.Context
import androidx.room.Room
import com.exercise.motiv8ai.websocket.data.GroceriesDatabase
import com.exercise.motiv8ai.websocket.data.dao.GroceryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object StorageModule {

    @Provides
    @Singleton
    @Synchronized
    fun provideDB(@ApplicationContext context: Context): GroceriesDatabase {
        return Room.databaseBuilder(
            context,
            GroceriesDatabase::class.java, "motiv8ai.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGroceryDao(db: GroceriesDatabase): GroceryDao = db.groceryDao()
}