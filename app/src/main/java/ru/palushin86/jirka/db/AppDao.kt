package ru.palushin86.jirka.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.palushin86.jirka.entities.Cabinet
import ru.palushin86.jirka.entities.Equipment
import ru.palushin86.jirka.entities.EquipmentType

@Dao
interface AppDao {

    @Query("SELECT * from equipmenttype ORDER BY name ASC")
    fun getEquipemtTypes(): LiveData<List<EquipmentType>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(equipmentType: EquipmentType)

    @Delete
    fun delete(equipmentType: EquipmentType)



    @Query("SELECT * from equipment ORDER BY name ASC")
    fun getEquipments(): LiveData<List<Equipment>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(equipment: Equipment)

    @Delete
    fun delete(equipment: Equipment)

    @Query("SELECT * from equipment WHERE cabinetId==:cabinetId")
    fun getEquipmentsByCabinetId(cabinetId: Int?): LiveData<List<Equipment>>

    @Query("SELECT * from equipment WHERE equipmentTypeId==:equipmentTypeId")
    fun getEquipmentsByEquipmentTypeId(equipmentTypeId: Int?): LiveData<List<Equipment>>



    @Query("SELECT * from cabinet ORDER BY name ASC")
    fun getCabinets(): LiveData<List<Cabinet>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cabinet: Cabinet)

    @Delete
    fun delete(cabinet: Cabinet)

}