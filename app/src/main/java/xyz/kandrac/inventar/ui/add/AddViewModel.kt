package xyz.kandrac.inventar.ui.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.kandrac.inventar.db.InventoryItem
import xyz.kandrac.inventar.inventory

class AddViewModel : ViewModel() {

    val complete = MutableLiveData(false)

    fun add(item: String) {
        viewModelScope.launch(Dispatchers.IO) {
            inventory.insertAll(
                InventoryItem(
                    name = item,
                    amount = 10.0,
                    type = "kg"
                )
            )
            complete.postValue(true)
        }
    }
}