package com.buildmyresume.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.buildmyresume.listener.RecyclerViewItem


@Entity(tableName = "objective_table")
data class ObjectiveModel(
    val userId: Int,
    val objective: String,
    @PrimaryKey(autoGenerate = false) val id: Int? = null
) : RecyclerViewItem {
    override val itemId: String
        get() = id.toString()
}