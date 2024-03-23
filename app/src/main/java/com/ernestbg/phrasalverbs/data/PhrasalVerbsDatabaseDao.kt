package com.ernestbg.phrasalverbs.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ernestbg.phrasalverbs.model.PhrasalVerb
import kotlinx.coroutines.flow.Flow

@Dao
interface PhrasalVerbsDatabaseDao {

    @Query("SELECT * FROM phrasal_verbs")
    fun getPhrasalVerbs(): Flow<List<PhrasalVerb>>

    @Query("SELECT * FROM phrasal_verbs where id=:id")
    suspend fun getPhrasalVerbById(id: String): PhrasalVerb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(phrasalVerb: PhrasalVerb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(phrasalVerbs: List<PhrasalVerb>) // Método para insertar una lista de PhrasalVerb

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(phrasalVerb: PhrasalVerb)

    @Query("DELETE FROM phrasal_verbs")
    suspend fun deleteAll()

    @Delete
    suspend fun deletePhrasalVerb(phrasalVerb: PhrasalVerb)


}
