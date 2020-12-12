package com.exercise.motiv8ai.websocket.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exercise.motiv8ai.websocket.shared.data.model.BaseEntity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "groceries"
)
data class Grocery(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String,

    @SerializedName("weight")
    @ColumnInfo(name = "weight")
    var weight: String,

    @SerializedName("bagColor")
    @ColumnInfo(name = "bg_color")
    var bgColor: String

) : BaseEntity()