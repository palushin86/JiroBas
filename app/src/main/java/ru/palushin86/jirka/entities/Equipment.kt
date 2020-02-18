package ru.palushin86.jirka.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Equipment(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val equipmentTypeId: Int,
    val cabinetId: Int,
    val name: String,
    val inventoryNumber: String,
    val number: Int,
    val ip: String? = null,
    val os: String? = null
)