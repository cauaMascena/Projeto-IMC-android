package com.example.projetoimc.repository

import android.content.Context
import com.example.projetoimc.model.Pesagem

class PesagemRepository(var context: Context) {

    fun getListaPesagem(): List<Pesagem> {
        val listaPesagem = listOf<Pesagem>()

    //**Preencher a lista de pesagem
        val  dados = context.getSharedPreferences(
                                        "usuario",
                                        Context.MODE_PRIVATE)

        val pesosString = dados.getString("pesagem", "")
        //""

        val pesos = pesosString!!.split(";").toTypedArray()

        val datasString = dados.getString("data_pesagem", "")
        //

        val datas = datasString!!.split(";").toTypedArray()

        //** Criar a lista de pesagem
        for (i in 0 until pesos.size){
            val pesagem = Pesagem(datas[i], pesos[i].toInt())
            listaPesagem.add(pesagem)
        }

        return listaPesagem

    }

}