package ru.palushin86.jirka.db

import ru.palushin86.jirka.entities.Cabinet
import ru.palushin86.jirka.entities.Equipment
import ru.palushin86.jirka.entities.EquipmentType

class DbRepository(private val appDao: AppDao) {
    val equipmentTypes = appDao.getEquipemtTypes()
    val equipments = appDao.getEquipment()
    val cabinets = appDao.getCabinet()

    fun insertEquipmentType(equipmentType: EquipmentType) {
        appDao.insert(equipmentType)
    }

    fun deleteEquipmentType(equipmentType: EquipmentType) {
        appDao.delete(equipmentType)
    }

    fun insert(cabinet: Cabinet) {
        appDao.insert(cabinet)
    }

    fun deleteEquipment(cabinet: Cabinet) {
        appDao.delete(cabinet)
    }

    fun insert(equipment: Equipment) {
        appDao.insert(equipment)
    }

    fun deleteEquipment(equipment: Equipment) {
        appDao.delete(equipment)
    }

}