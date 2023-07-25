package com.buildmyresume.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonalDetailsDao {

    @Insert
    fun insert(personalDetailsModel: PersonalDetailsModel)

    @Update
    fun update(personalDetailsModel: PersonalDetailsModel)

    @Delete
    fun delete(personalDetailsModel: PersonalDetailsModel)

    @Query("delete from personal_details_table")
    fun deleteAllPersonalDetails()

    @Query("select * from personal_details_table order by id desc")
    fun getAllPersonalDetails(): LiveData<List<PersonalDetailsModel>>
}