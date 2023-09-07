package com.buildmyresume.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineExceptionHandler

@Database(entities = [PersonalDetailsModel::class, ObjectiveModel::class, WorkExperienceModel::class], version = 1)
abstract class ResumeLocalDatabase : RoomDatabase() {

    abstract fun personalDetailsDao(): PersonalDetailsDao
    abstract fun objectiveDao(): ObjectiveDao

    abstract fun workExpDao(): WorkExperienceDao

    companion object {
        private var instance: ResumeLocalDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): ResumeLocalDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, ResumeLocalDatabase::class.java,
                    "resume_local_database")
                    .fallbackToDestructiveMigration()
                    /*.addCallback(roomCallback)*/
                    .build()

            return instance!!

        }


    }



}