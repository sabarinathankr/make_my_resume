package com.buildmyresume.roomdb

import android.app.Application
import androidx.lifecycle.LiveData

class PersonalDetailsRepository(application: Application) {

    private var personalDetailsDao: PersonalDetailsDao
    private var allNotes: LiveData<List<PersonalDetailsModel>>

    private val database = PersonalDetailsDatabase.getInstance(application)

    init {
        personalDetailsDao = database.noteDao()
        allNotes = personalDetailsDao.getAllPersonalDetails()
    }

    fun insert(personalDetailsModel: PersonalDetailsModel) {
        subscribeOnBackground {
            personalDetailsDao.insert(personalDetailsModel)
        }
    }

    fun update(personalDetailsModel: PersonalDetailsModel) {
        subscribeOnBackground {
            personalDetailsDao.update(personalDetailsModel)
        }
    }

    fun delete(personalDetailsModel: PersonalDetailsModel) {
        subscribeOnBackground {
            personalDetailsDao.delete(personalDetailsModel)
        }
    }

    fun deleteAllPersonalDetails() {
        subscribeOnBackground {
            personalDetailsDao.deleteAllPersonalDetails()
        }
    }

    fun getAllPersonalDetails(): LiveData<List<PersonalDetailsModel>> {
        return allNotes
    }



}