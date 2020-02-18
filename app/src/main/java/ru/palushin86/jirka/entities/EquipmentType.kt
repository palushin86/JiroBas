package ru.palushin86.jirka.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EquipmentType(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String
)