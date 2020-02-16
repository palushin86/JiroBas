package ru.palushin86.jirka.data.contracts

import ru.palushin86.jirka.entity.Cabinet
import ru.palushin86.jirka.entity.Equipment
import ru.palushin86.jirka.entity.EquipmentType

interface EquipmentDataManager {
    var equipments: List<Equipment>
    var equipmentTypes: List<EquipmentType>
    var cabinets: List<Cabinet>
}