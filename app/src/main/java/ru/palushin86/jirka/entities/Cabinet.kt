package ru.palushin86.jirka.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cabinet(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val owner: String,
    val level: String
)