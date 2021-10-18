package com.example.projetoimc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_perfil.*
import java.util.*

class PerfilActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfi: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        editEmail = findViewById(R.id.edit_email)
        editSenha = findViewById(R.id.edit_senha)
        editNome = findViewById(R.id.edit_nome)
        editProfi = findViewById(R.id.edit_profissao)

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
        return valido
    }
}