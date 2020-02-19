package ru.palushin86.jirka.ui.cabinets

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.palushin86.jirka.App
import ru.palushin86.jirka.entities.Cabinet
import ru.palushin86.jirka.entities.Equipment
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CabinetsViewModel : ViewModel() {
    private val dao = App.database.appDao()
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    var cabinets: MutableList<Cabinet> = ArrayList()

    fun getCabinets(): LiveData<List<Cabinet>> = dao.getCabinets()

    fun insertCabinet(cabinet: Cabinet) {
        executor.execute { dao.insert(cabinet) }
    }

    fun deleteCabinet(cabinet: Cabinet) {
        executor.execute { dao.delete(cabinet) }
    }

    fun getEquipmentsByCabinet(item: Cabinet): LiveData<List<Equipment>> = dao.getEquipmentsByCabinetId(item.id)

}