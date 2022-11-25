package xyz.kandrac.inventar.ui.add

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import xyz.kandrac.inventar.R

class AddActivity : AppCompatActivity() {

    private val viewModel: AddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val input = findViewById<EditText>(R.id.input)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            viewModel.add(input.text.toString())
        }

        viewModel.complete.observe(this) { complete ->
            if (complete) {
                button.isEnabled = false
                Snackbar.make(button, "Polozka pridana", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK") { finish() }
                    .show()
            }
        }
    }
}