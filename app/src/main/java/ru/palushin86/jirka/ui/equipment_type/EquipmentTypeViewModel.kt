package ru.palushin86.jirka.ui.equipment_type

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.palushin86.jirka.App
import ru.palushin86.jirka.db.DbRepository
import ru.palushin86.jirka.entities.EquipmentType
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EquipmentTypeViewModel : ViewModel() {
    private val appDao: DbRepository = DbRepository(App.database.appDao())
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    var types: MutableList<EquipmentType> = ArrayList()

    fun getEquipmentTypes(): LiveData<List<EquipmentType>> = appDao.equipmentTypes

    fun insertEquipmentType(equipmentType: EquipmentType) {
        executor.execute { appDao.insertEquipmentType(equipmentType) }
    }

    fun deleteEquipmentType(equipmentType: EquipmentType) {
        executor.execute { appDao.deleteEquipmentType(equipmentType) }
    }
}