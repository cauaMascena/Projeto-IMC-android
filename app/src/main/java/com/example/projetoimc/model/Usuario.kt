package com.example.projetoimc.model

import java.time.LocalDate

data class Usuario(
    var id: Int,
    var nome: String,
    var email: String,
    var senha: String,
    var peso: Int,
    var altura: Float,
    var pesagem: Pesagem,
    var dataNascimento: LocalDate,
    var dataPesagem: Pesagem,
    var profissao: String,
    var sexo: Char

    )

