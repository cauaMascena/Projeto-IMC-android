package com.example.projetoimc.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.projetoimc.R
import com.example.projetoimc.model.Usuario
import com.example.projetoimc.utils.convertStringToLocalDate
import kotlinx.android.synthetic.main.activity_perfil.*
import java.time.LocalDate
import java.util.*

const val CODE_IMAGE = 100

class NovoUsuario : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfi: EditText
    lateinit var editAltura: EditText
    lateinit var editNasc: EditText
    lateinit var radioF: RadioButton
    lateinit var radioM: RadioButton
    lateinit var tvTrocarFoto: TextView
    lateinit var ivFotoPerfil: ImageView
    var imageBitmap: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        editNome = findViewById(R.id.edit_nome)
        editEmail = findViewById(R.id.edit_email)
        editSenha = findViewById(R.id.edit_senha)
        editProfi = findViewById(R.id.edit_profissao)
        editAltura = findViewById(R.id.edit_altura)
        editNasc = findViewById(R.id.edit_data_nascimento)
        radioF = findViewById(R.id.radio_feminino)
        radioM = findViewById(R.id.radio_masculino)
        tvTrocarFoto = findViewById(R.id.tv_trocar_foto)
        ivFotoPerfil = findViewById(R.id.iv_foto_perfil)


        supportActionBar!!.title = "Novo Usuario"
//
        // Criar o calendário
        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        editNasc.setOnClickListener {
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->

                    var diaFinal = _dia
                    var mesFinal = _mes + 1

                    var mesString = "$mesFinal"
                    var diaString = "$diaFinal"

                    if (mesFinal < 10) {
                        mesString = "0$mesFinal"
                    }

                    if (diaFinal < 10) {
                        diaString = "0$diaFinal"
                    }

                    Log.i("xpto", _dia.toString())
                    Log.i("xpto", _mes.toString())

                    editNasc.setText("$diaString/$mesString/$_ano")
                }, ano, mes, dia
            )
            dpd.show()
        }

    }
    //criando calendário

    override fun onActivityResult(requestCode: Int, resultCode: Int, imagem: Intent?) {
        super.onActivityResult(requestCode, resultCode, imagem)

        if (requestCode == CODE_IMAGE && resultCode == -1){
            // Recuperar a imagem do stream
            val fluxoImagem = contentResolver.openInputStream(imagem!!.data!!)

            // Converter os bits em um bitmap
            imageBitmap = BitmapFactory.decodeStream(fluxoImagem)

            // Colocar o bitmap no ImageView
            ivFotoPerfil.setImageBitmap(imageBitmap)

        }

    }

    private fun abrirGaleria() {

        // Abrir a galeria de imagens do dispositivo
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"

        // Abrir a Activity responsável por exibir as imagens
        // Esta Activity retornará o conteúdo selecionado
        // para o nosso app
        startActivityForResult(
            Intent.createChooser(intent,
                "Escolha uma foto"),
            CODE_IMAGE
        )

    }


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
                   editAltura.text.toString().toFloat(),
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
           val editor = dados.edit()
           editor.putInt("id", usuario.id)
           editor.putString("nome", usuario.nome)
           editor.putString("email", usuario.email)
           editor.putString("senha", usuario.senha)
           editor.putInt("peso", usuario.peso)
           editor.putFloat("altura", usuario.altura)
           editor.putString("dataNascimento", usuario.dataNascimento.toString())
           editor.putString("profissao", usuario.profissao)
           editor.putString("sexo", usuario.sexo.toString())
           editor.apply()
       }

        Toast.makeText(this, "Usuário  Cadastrado", Toast.LENGTH_SHORT).show()

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