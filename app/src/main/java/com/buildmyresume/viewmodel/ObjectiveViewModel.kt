package com.buildmyresume.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.buildmyresume.roomdb.ObjectiveModel
import com.buildmyresume.roomdb.ObjectiveRepository

class ObjectiveViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = ObjectiveRepository(app)
    private val getAllObjective = repository.getAllObjective()

    fun insert(objectiveModel: ObjectiveModel) {
        repository.insert(objectiveModel)
    }

    fun update(objectiveData: String, userId: Int) {
        repository.update(objectiveData, userId)
    }

    fun delete(userId: Int) {
        repository.delete(userId)
    }

    fun deleteAllObjective() {
        repository.deleteAllObjective()
    }

    fun getAllObjective(): LiveData<List<ObjectiveModel>> {
        return getAllObjective
    }

    fun getSelectedObjective(id:Int): LiveData<List<ObjectiveModel>> {
        return repository.getSelectedObjective(id)
    }
}