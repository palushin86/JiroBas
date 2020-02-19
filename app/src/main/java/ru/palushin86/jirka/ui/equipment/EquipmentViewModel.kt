package ru.palushin86.jirka.ui.equipment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.palushin86.jirka.App
import ru.palushin86.jirka.entities.Cabinet
import ru.palushin86.jirka.entities.Equipment
import ru.palushin86.jirka.entities.EquipmentType
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EquipmentViewModel : ViewModel() {
    private val dao = App.database.appDao()
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    var equipmentTypes: MutableList<EquipmentType> = ArrayList()
    var equipments: MutableList<Equipment> = ArrayList()
    var cabinets: MutableList<Cabinet> = ArrayList()

    fun getEquipments(): LiveData<List<Equipment>> = dao.getEquipments()
    fun getEquipmentTypes(): LiveData<List<EquipmentType>> = dao.getEquipemtTypes()
    fun getCabinets(): LiveData<List<Cabinet>> = dao.getCabinets()

    fun insertEquipment(equipment: Equipment) {
        executor.execute { dao.insert(equipment) }
    }

    fun deleteEquipment(equipment: Equipment) {
        executor.execute { dao.delete(equipment) }
    }
}