package com.example.projetoimc.ui

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.example.projetoimc.R
import com.example.projetoimc.model.Usuario
import com.example.projetoimc.utils.convertStringToLocalDate
import kotlinx.android.synthetic.main.activity_perfil.*
import java.time.LocalDate
import java.util.*

class NovoUsuario : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfi: EditText
    lateinit var editAltura: EditText
    lateinit var editNasc: EditText
    lateinit var radioSex: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        editNome = findViewById(R.id.edit_nome)
        editEmail = findViewById(R.id.edit_email)
        editSenha = findViewById(R.id.edit_senha)
        editProfi = findViewById(R.id.edit_profissao)
        editAltura = findViewById(R.id.edit_altura)
        editNasc = findViewById(R.id.edit_data_nascimento)
        radioSex = findViewById(R.id)

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

        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

       if (ValidarCampos()) {
           //Criar o objeto usuario
               val nascimento = convertStringToLocalDate(edit_data_nascimento.text.toString())

               val usuario = Usuario(
                   1,
                   editNome.text.toString(),
                   editEmail.text.toString(),
                   editSenha.text.toString(),
                   0,
                   editAltura.text.toString().toDouble(),
                   LocalDate.of(
                       nascimento.year,
                       nascimento.monthValue,
                       nascimento.dayOfMonth,
                       ),
                   editProfi.text.toString(),
                   if (radioF.isChecked) 'F' else 'M'


               )

           //Salvar o registro
           //Em um SharePreferences

           //A instrução abaixo irá criar um
           //arquivo sharePreferences se não existir
           //Se existir ele será aberto para edição
           val dados = getSharedPreferences("usuario", Context.MODE_PRIVATE)

           // Vamos criar o objeto que permitirá a edição dos dados
           // do arquivo SharePreferences
           val editor = dados;
       }

        return true
    }

    fun ValidarCampos() : Boolean {
        var valido = true
        if (editEmail.text.isEmpty()) {
            editEmail.error = "E-mail é obrigatório"
            valido = false
        }
        if (editSenha.text.isEmpty()) {
            editSenha.error = "Senha é obrigatório"
            valido = false

        }
        if (editNome.text.isEmpty()) {
            editNome.error = "Nome é obrigatório"
            valido = false

        }
        if (editProfi.text.isEmpty()) {
            editProfi.error = "Profissão é obrigatória"
            valido = false

        }
        if (editAltura.text.isEmpty()) {
            editAltura.error = "Altura é obrigatória"
            valido = false

        }
        return valido
    }
}