package com.example.projetoimc.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.example.projetoimc.R
import java.util.*

class PerfilActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfi: EditText
    lateinit var editAltura: EditText
    lateinit var editNasc: EditText
    lateinit var editSex: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        editEmail = findViewById(R.id.edit_email)
        editSenha = findViewById(R.id.edit_senha)
        editNome = findViewById(R.id.edit_nome)
        editProfi = findViewById(R.id.edit_profissao)
        editAltura = findViewById(R.id.edit_altura)
        editNasc = findViewById(R.id.edit_data_nascimento)

        supportActionBar!!.title = "Novo Usuario"
//
        val calendario = Calendar.getInstance()

        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val etDataNacimento = findViewById<EditText>(R.id.edit_data_nascimento)

        etDataNacimento.setOnClickListener {
            val dp = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->
                    etDataNacimento.setText(
                        "$_dia/${_mes + 1}/$_ano"
                    )
                },
                ano,
                mes,
                dia
            )
            dp.show()
        }

    }


    //criando calendário


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_salvar, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (editEmail.text.isEmpty()){
            editEmail.error = "O e-mail é obrigatório!"
        }
        if (editSenha.text.isEmpty()){
            editSenha.error = "A senha é obrigatória!"
        }
        if (editNome.text.isEmpty()){
            editNome.error = "O nome é obrigatório!"
        }
        if (editProfi.text.isEmpty()){
            editProfi.error = "A profissão é obrigatória!"
        }
        if (editAltura.text.isEmpty()){
            editAltura.error = "A altura é obrigatória!"
        }

        return true
    }
    fun ValidarCampos() : Boolean {
        var valido = true
        if (editEmail.text.isEmpty()) {
            editEmail.error = "E-mail é obrigatório"
        }
        if (editSenha.text.isEmpty()) {
            editSenha.error = "Senha é obrigatório"
        }
        if (editNome.text.isEmpty()) {
            editNome.error = "Nome é obrigatório"
        }
        if (editProfi.text.isEmpty()) {
            editProfi.error = "Profissão é obrigatória"
        }
        if (editAltura.text.isEmpty()) {
            editAltura.error = "Altura é obrigatória"
        }
        return valido
    }
}