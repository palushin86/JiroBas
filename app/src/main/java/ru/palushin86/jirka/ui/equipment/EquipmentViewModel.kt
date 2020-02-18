package ru.palushin86.jirka.ui.equipment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.palushin86.jirka.App
import ru.palushin86.jirka.db.DbRepository
import ru.palushin86.jirka.entities.Cabinet
import ru.palushin86.jirka.entities.Equipment
import ru.palushin86.jirka.entities.EquipmentType
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EquipmentViewModel : ViewModel() {
    private val appDao: DbRepository = DbRepository(App.database.appDao())
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    var equipmentTypes: MutableList<EquipmentType> = ArrayList()
    var equipments: MutableList<Equipment> = ArrayList()
    var cabinets: MutableList<Cabinet> = ArrayList()

    fun getEquipments(): LiveData<List<Equipment>> = appDao.equipments
    fun getEquipmentTypes(): LiveData<List<EquipmentType>> = appDao.equipmentTypes
    fun getCabinets(): LiveData<List<Cabinet>> = appDao.cabinets

    fun insertEquipment(equipment: Equipment) {
        executor.execute { appDao.insert(equipment) }
    }

    fun deleteEquipment(equipment: Equipment) {
        executor.execute { appDao.deleteEquipment(equipment) }
    }
}