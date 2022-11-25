package xyz.kandrac.inventar.ui.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import xyz.kandrac.inventar.R
import xyz.kandrac.inventar.db.InventoryItem

// tu su zlozite veci, ale zaujimat ta moze, co sa zobrazi v zozname
// ak chces zmenit vizual
// 1. zmen item_inventory.xml
// 2. najdi v tomto subore InventoryViewHolder a pridaj tam tvoj novy view (pozri name a weight)
// 3. content mu nastav v onBindViewHolder
class InventoryActivity : AppCompatActivity() {

    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        val recyclerAdapter = InventoryAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        viewModel.items.observe(this) { items ->
            when(items) {
                null -> { } // Loading
                else -> {
                    recyclerAdapter.items = items
                }
            }
        }
    }

    private class InventoryAdapter: RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>() {

        var items: List<InventoryItem>? = null
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = InventoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_inventory, parent, false)
        )

        override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
            val item = items!![position]
            holder.name.text = item.name
            holder.weight.text = "${item.amount} ${item.type}"
        }

        override fun getItemCount() = items?.size ?: 0

        private class InventoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name = view.findViewById<TextView>(R.id.item_name)
            val weight = view.findViewById<TextView>(R.id.item_weight)
        }
    }
}