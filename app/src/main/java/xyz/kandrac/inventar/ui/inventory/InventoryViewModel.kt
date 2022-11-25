package xyz.kandrac.inventar.ui.inventory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.kandrac.inventar.db.InventoryItem
import xyz.kandrac.inventar.inventory

class InventoryViewModel : ViewModel() {

    val items: MutableLiveData<List<InventoryItem>?> = MutableLiveData(null)

    init {
        // toto sa zavola hned ako sa vytvori viewmodel
        // viewModelScope.launch(Dispatchers.IO) znamena, ze sa to spusti na novom threade
        // viewModelScope sa da pouzit len vo ViewModel
        viewModelScope.launch(Dispatchers.IO) {
            items.postValue(inventory.getAll())
        }
    }
}