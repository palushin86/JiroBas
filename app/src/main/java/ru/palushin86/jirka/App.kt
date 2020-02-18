package ru.palushin86.jirka

import android.app.Application
import ru.palushin86.jirka.db.AppDatabase

class App : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase
            .getInstance(this)
    }
}