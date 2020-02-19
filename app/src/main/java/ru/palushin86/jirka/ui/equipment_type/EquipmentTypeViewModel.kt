package ru.palushin86.jirka.ui.equipment_type

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.palushin86.jirka.App
import ru.palushin86.jirka.entities.Equipment
import ru.palushin86.jirka.entities.EquipmentType
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EquipmentTypeViewModel : ViewModel() {
    private val dao = App.database.appDao()
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    var types: MutableList<EquipmentType> = ArrayList()

    fun getEquipmentTypes(): LiveData<List<EquipmentType>> = dao.getEquipemtTypes()

    fun insertEquipmentType(equipmentType: EquipmentType) {
        executor.execute { dao.insert(equipmentType) }
    }

    fun deleteEquipmentType(equipmentType: EquipmentType) {
        executor.execute { dao.delete(equipmentType) }
    }

    fun getEquipmentsByEquipmetType(deletedItem: EquipmentType): LiveData<List<Equipment>> {
        return dao.getEquipmentsByEquipmentTypeId(deletedItem.id)
    }
}