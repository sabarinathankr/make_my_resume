package com.buildmyresume.listener

import com.buildmyresume.roomdb.PersonalDetailsModel

fun interface UserListClickListener {
    fun onItemClick(item: PersonalDetailsModel)
}