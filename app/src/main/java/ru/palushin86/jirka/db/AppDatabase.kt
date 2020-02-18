package ru.palushin86.jirka.db

import ru.palushin86.jirka.entities.Cabinet
import ru.palushin86.jirka.entities.Equipment
import ru.palushin86.jirka.entities.EquipmentType

import android.content.Context
import androidx.room.*

@Database(
    entities = [
        Cabinet::class,
        Equipment::class,
        EquipmentType::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}