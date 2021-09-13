package com.example.projetoimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalcular = findViewById<Button>(R.id.button_calcular)
        val etPeso = findViewById<EditText>(R.id.peso)
        val etAltura = findViewById<EditText>(R.id.altura)
        val textResultado = findViewById<TextView>(R.id.resultado)
        btnCalcular.setOnClickListener {
            val peso = etPeso.text.toString().toInt()
            val altura = etAltura.text.toString().toDouble()

            val imc = calcularImc(peso, altura)

            textResultado.text = String.format("%.1f", imc)
        }
    }
}
