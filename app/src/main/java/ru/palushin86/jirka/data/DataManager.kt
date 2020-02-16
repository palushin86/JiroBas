package ru.palushin86.jirka.data

import ru.palushin86.jirka.data.contracts.CabinetsDataManager
import ru.palushin86.jirka.data.contracts.EquipmentDataManager
import ru.palushin86.jirka.data.contracts.EquipmentTypeDataManager
import ru.palushin86.jirka.entity.Cabinet
import ru.palushin86.jirka.entity.Equipment
import ru.palushin86.jirka.entity.EquipmentType

class DataManager : EquipmentTypeDataManager,
    EquipmentDataManager,
    CabinetsDataManager {

    override var equipmentTypes: List<EquipmentType> = mutableListOf(
        EquipmentType("Компьютер"),
        EquipmentType("Принтер"),
        EquipmentType("Сканер")
    )

    override var equipments: List<Equipment> = mutableListOf(
        Equipment(0, 0,"комп1", "0123", 1),
        Equipment(1, 1,"принтер1", "0123", 1),
        Equipment(2, 2,"сканер1", "0123", 1)
    )

    override var cabinets: List<Cabinet> = mutableListOf(
        Cabinet("Компьютер мой", "я", "3"),
        Cabinet("Компьютер не мой", "не я", "2"),
        Cabinet("Компьютер твой", "ты", "1")
    )

}