package com.ernestbg.phrasalverbs.di

import android.content.Context
import androidx.room.Room
import com.ernestbg.phrasalverbs.data.PhrasalVerbsDatabase
import com.ernestbg.phrasalverbs.data.PhrasalVerbsDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePhrasalVerbsDatabase(@ApplicationContext context: Context): PhrasalVerbsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PhrasalVerbsDatabase::class.java,
            "phrasal_verbs_db.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providePhrasalVerbsDao(database: PhrasalVerbsDatabase): PhrasalVerbsDatabaseDao {
        return database.phrasalVerbsDao()
    }
}
