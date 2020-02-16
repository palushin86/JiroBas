package ru.palushin86.jirka.entity

data class Equipment(
    val equipmentTypeId: Int,
    val cabinetId: Int,
    val name: String,
    val inventoryNumber: String,
    val number: Int,
    val ip: String? = null,
    val os: String? = null
)