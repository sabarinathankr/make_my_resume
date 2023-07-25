package com.buildmyresume.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [PersonalDetailsModel::class], version = 1)
abstract class PersonalDetailsDatabase : RoomDatabase() {

    abstract fun noteDao(): PersonalDetailsDao

    companion object {
        private var instance: PersonalDetailsDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): PersonalDetailsDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, PersonalDetailsDatabase::class.java,
                    "personal_details_database")
                    .fallbackToDestructiveMigration()
                    /*.addCallback(roomCallback)*/
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }


    }



}