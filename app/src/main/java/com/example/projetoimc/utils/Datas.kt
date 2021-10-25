package com.example.projetoimc.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun convertStringToLocalDate(brazilDate: String) : LocalDate {

    val dateFormatterFromBrazil = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val LocalDateFormat = LocalDate.parse(brazilDate, dateFormatterFromBrazil)

    return LocalDateFormat

}