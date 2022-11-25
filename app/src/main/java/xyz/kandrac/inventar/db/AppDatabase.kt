package xyz.kandrac.inventar.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [InventoryItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryItemDao
}