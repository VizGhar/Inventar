package xyz.kandrac.inventar

import android.content.Context
import androidx.room.Room
import xyz.kandrac.inventar.db.AppDatabase
import xyz.kandrac.inventar.db.InventoryItemDao

lateinit var database: AppDatabase
lateinit var inventory: InventoryItemDao

fun initDatabase(appContext: Context) {

    database = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java, "databaza"
    ).build()

    inventory = database.inventoryDao()
}
