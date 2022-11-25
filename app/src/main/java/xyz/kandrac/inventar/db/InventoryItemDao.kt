package xyz.kandrac.inventar.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InventoryItemDao {

    @Query("SELECT * FROM inventory_items")
    fun getAll(): List<InventoryItem>

    @Insert
    fun insertAll(vararg items: InventoryItem)

    @Delete
    fun delete(item: InventoryItem)

}
