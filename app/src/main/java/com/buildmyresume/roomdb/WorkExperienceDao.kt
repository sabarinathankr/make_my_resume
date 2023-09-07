package com.buildmyresume.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkExperienceDao {
    @Insert
    fun insert(workExpModel: WorkExperienceModel)

    @Query("UPDATE work_exp_table SET experience = :wrkExp WHERE userId = :id")
    fun update(wrkExp: String, id: Int)

    @Query("DELETE FROM work_exp_table  WHERE userId = :id")
    fun delete(id: Int)

    @Query("delete from work_exp_table")
    fun deleteAllWorkExp()

    @Query("select * from work_exp_table")
    fun getAllWorkExp(): LiveData<List<WorkExperienceModel>>

    @Query("select * from work_exp_table  where userId = :userId")
    fun getSelectedWorkExp(userId: Int): LiveData<List<WorkExperienceModel>>
}