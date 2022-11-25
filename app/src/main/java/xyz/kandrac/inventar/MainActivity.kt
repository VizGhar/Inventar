package xyz.kandrac.inventar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import xyz.kandrac.inventar.ui.add.AddActivity
import xyz.kandrac.inventar.ui.inventory.InventoryActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_1).setOnClickListener {
            start<InventoryActivity>()
        }
        findViewById<Button>(R.id.button_2).setOnClickListener {
            start<AddActivity>()
        }
    }
}