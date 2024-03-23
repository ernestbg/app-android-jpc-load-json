package com.ernestbg.phrasalverbs.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ernestbg.phrasalverbs.model.PhrasalVerb

@Database(entities = [PhrasalVerb::class], version = 1, exportSchema = false)
abstract class PhrasalVerbsDatabase : RoomDatabase() {

    abstract fun phrasalVerbsDao(): PhrasalVerbsDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: PhrasalVerbsDatabase? = null

        fun getDatabase(context: Context): PhrasalVerbsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhrasalVerbsDatabase::class.java,
                    "phrasal_verbs_database.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
