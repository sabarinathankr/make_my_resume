package com.buildmyresume.roomdb

import android.app.Application
import androidx.lifecycle.LiveData

class WorkExperienceRepository(application: Application) {

    private var workExperienceDao: WorkExperienceDao
    private var workExpList: LiveData<List<WorkExperienceModel>>

    private val database = ResumeLocalDatabase.getInstance(application)

    init {
        workExperienceDao = database.workExpDao()
        workExpList = workExperienceDao.getAllWorkExp()

    }

    fun insert(workExpModel: WorkExperienceModel) {
        subscribeOnBackground {
            workExperienceDao.insert(workExpModel)
        }
    }

    fun update(objectiveData: String, userId: Int) {
        subscribeOnBackground {
            workExperienceDao.update(objectiveData, userId)
        }
    }

    fun delete(userId: Int) {
        subscribeOnBackground {
            workExperienceDao.delete(userId)
        }
    }

    fun deleteAllObjective() {
        subscribeOnBackground {
            workExperienceDao.deleteAllWorkExp()
        }
    }

    fun getAllWorkExp(): LiveData<List<WorkExperienceModel>> {
        return workExpList
    }

    fun getSelectedWorkExp(id: Int): LiveData<List<WorkExperienceModel>> {
        return  workExperienceDao.getSelectedWorkExp(id)
    }
}