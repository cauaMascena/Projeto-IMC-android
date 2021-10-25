package com.example.projetoimc.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.projetoimc.R

class LoginActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvHello = findViewById<TextView>(R.id.tv_criar_conta)
        tvHello.setOnClickListener {
            val abrirActivityCadastro = Intent(this, NovoUsuario::class.java)
            //contexto e "destino" ||||| ::class.java - instancia a classe

            startActivity(abrirActivityCadastro)
        }
    }

}