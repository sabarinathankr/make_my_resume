package com.buildmyresume.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.buildmyresume.listener.RecyclerViewItem

@Entity(tableName = "work_exp_table")
data class WorkExperienceModel(
    val userId: Int,
    val experience: String,
    @PrimaryKey(autoGenerate = false) val id: Int? = null,
) :
    RecyclerViewItem {
    override val itemId: String
        get() = id.toString()
}
