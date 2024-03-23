package com.ernestbg.phrasalverbs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName="phrasal_verbs")
data class PhrasalVerb(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo
    val headword: String,
    val definition: String,
    val guideword: String,
    val example: String

)