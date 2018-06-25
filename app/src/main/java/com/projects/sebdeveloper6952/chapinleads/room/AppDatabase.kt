package com.projects.sebdeveloper6952.chapinleads.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData.Lead

@Database(
        entities = [(Lead::class)],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun leadsModel(): LeadDao

    companion object {
        private val DB_NAME = "app.db"
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}