package com.example.lab1

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editFrom = findViewById<EditText>(R.id.editFrom)
        val editTo = findViewById<EditText>(R.id.editTo)
        val radioGroupTime = findViewById<RadioGroup>(R.id.radioGroupTime)
        val btnOk = findViewById<Button>(R.id.btnOk)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        btnOk.setOnClickListener {
            val fromCity = editFrom.text.toString().trim()
            val toCity = editTo.text.toString().trim()
            val checkedId = radioGroupTime.checkedRadioButtonId

            if (fromCity.isEmpty() || toCity.isEmpty() || checkedId == -1) {
                Toast.makeText(this, "Завершіть введення всіх даних!", Toast.LENGTH_SHORT).show()
            } else {
                val selectedRadio = findViewById<RadioButton>(checkedId)
                val time = selectedRadio.text.toString()

                txtResult.text = "Маршрут: $fromCity — $toCity\nЧас: $time"
            }
        }
    }
}