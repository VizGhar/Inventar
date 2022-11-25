package xyz.kandrac.inventar

import android.app.Application

// Toto nechytat :)
// tu je len inicializacia databazy
class InventoryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDatabase(this)
    }

}