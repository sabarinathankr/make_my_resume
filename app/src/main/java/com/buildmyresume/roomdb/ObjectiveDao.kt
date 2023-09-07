package com.buildmyresume.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface ObjectiveDao {

    @Insert
    fun insert(objective: ObjectiveModel)

    @Query("UPDATE objective_table SET objective = :objectiveData WHERE userId = :id")
    fun update(objectiveData: String, id: Int)

    @Query("DELETE FROM objective_table  WHERE userId = :id")
    fun delete(id: Int)

    @Query("delete from objective_table")
    fun deleteAllObjective()

    @Query("select * from objective_table")
    fun getAllObjective(): LiveData<List<ObjectiveModel>>

    @Query("select * from objective_table  where userId = :userId")
    fun getSelectedObjective(userId: Int): LiveData<List<ObjectiveModel>>
}