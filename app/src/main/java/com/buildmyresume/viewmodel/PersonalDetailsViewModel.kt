package com.buildmyresume.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.buildmyresume.roomdb.PersonalDetailsModel
import com.buildmyresume.roomdb.PersonalDetailsRepository

class PersonalDetailsViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = PersonalDetailsRepository(app)
    private val allNotes = repository.getAllPersonalDetails()

    fun insert(note: PersonalDetailsModel) {
        repository.insert(note)
    }

    fun update(note: PersonalDetailsModel) {
        repository.update(note)
    }

    fun delete(note: PersonalDetailsModel) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllPersonalDetails()
    }

    fun getAllNotes(): LiveData<List<PersonalDetailsModel>> {
        return allNotes
    }


}