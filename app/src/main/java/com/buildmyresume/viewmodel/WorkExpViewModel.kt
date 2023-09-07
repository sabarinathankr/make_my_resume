package com.buildmyresume.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.buildmyresume.roomdb.WorkExperienceModel
import com.buildmyresume.roomdb.WorkExperienceRepository

class WorkExpViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = WorkExperienceRepository(app)
    private val getAllWorkExp = repository.getAllWorkExp()

    fun insert(workExpModel: WorkExperienceModel) {
        repository.insert(workExpModel)
    }

    fun update(exp: String, userId: Int) {
        repository.update(exp, userId)
    }

    fun delete(userId: Int) {
        repository.delete(userId)
    }

    fun deleteAllWorkExp() {
        repository.deleteAllObjective()
    }

    fun getAllWorkExp(): LiveData<List<WorkExperienceModel>> {
        return getAllWorkExp
    }

    fun getSelectedWorkExp(id: Int): LiveData<List<WorkExperienceModel>> {
        return repository.getSelectedWorkExp(id)
    }
}