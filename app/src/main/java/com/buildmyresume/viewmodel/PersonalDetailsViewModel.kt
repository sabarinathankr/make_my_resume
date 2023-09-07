package com.buildmyresume.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.buildmyresume.roomdb.PersonalDetailsModel
import com.buildmyresume.roomdb.PersonalDetailsRepository

class PersonalDetailsViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = PersonalDetailsRepository(app)
    private val allPersonalDetails = repository.getAllPersonalDetails()

    fun insert(personalDetailsModel: PersonalDetailsModel) {
        repository.insert(personalDetailsModel)
    }

    fun update(personalDetailsModel: PersonalDetailsModel) {
        repository.update(personalDetailsModel)
    }

    fun delete(personalDetailsModel: PersonalDetailsModel) {
        repository.delete(personalDetailsModel)
    }

    fun deleteAllNotes() {
        repository.deleteAllPersonalDetails()
    }

    fun getAllPersonalDetails(): LiveData<List<PersonalDetailsModel>> {
        return allPersonalDetails
    }


}