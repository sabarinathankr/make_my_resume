package com.buildmyresume.roomdb

import android.app.Application
import androidx.lifecycle.LiveData

class ObjectiveRepository(application: Application) {

    private var objectiveDao: ObjectiveDao
    private var objectiveList: LiveData<List<ObjectiveModel>>

    private val database = ResumeLocalDatabase.getInstance(application)

    init {
        objectiveDao = database.objectiveDao()
        objectiveList = objectiveDao.getAllObjective()

    }

    fun insert(objectiveModel: ObjectiveModel) {
        subscribeOnBackground {
            objectiveDao.insert(objectiveModel)
        }
    }

    fun update(objectiveData: String, userId: Int) {
        subscribeOnBackground {
            objectiveDao.update(objectiveData, userId)
        }
    }

    fun delete(userId: Int) {
        subscribeOnBackground {
            objectiveDao.delete(userId)
        }
    }

    fun deleteAllObjective() {
        subscribeOnBackground {
            objectiveDao.deleteAllObjective()
        }
    }

    fun getAllObjective(): LiveData<List<ObjectiveModel>> {
        return objectiveList
    }

    fun getSelectedObjective(id: Int): LiveData<List<ObjectiveModel>> {
        return  objectiveDao.getSelectedObjective(id)
    }
}