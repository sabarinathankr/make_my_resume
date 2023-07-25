package com.buildmyresume.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.buildmyresume.listener.RecyclerViewItem

@Entity(tableName = "personal_details_table")
data class PersonalDetailsModel(
    val name: String,
    val profession: String,
    val address: String,
    val phoneNumber: String,
    val email: String,
    val website: String,
    val dob: String,
    @PrimaryKey(autoGenerate = false) val id: Int? = null,
) :
    RecyclerViewItem {
    override val itemId: String
        get() = id.toString()
}