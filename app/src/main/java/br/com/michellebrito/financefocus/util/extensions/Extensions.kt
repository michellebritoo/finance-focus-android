package br.com.michellebrito.financefocus.util.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun String?.orEmpty(): String {
    return this ?: ""
}

fun String?.isEmptyOrBlank(): Boolean {
    return this?.isEmpty() == true || this?.isBlank() == true
}

fun String.isValidDate(): Boolean {
    if (!this.isValidDateFormat()) {
        return false
    }

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    dateFormat.isLenient = false
    return try {
        dateFormat.parse(this)
        true
    } catch (e: Exception) {
        false
    }
}

fun String.isValidDateFormat(): Boolean {
    val datePattern = "^\\d{2}/\\d{2}/\\d{4}$"
    return this.matches(Regex(datePattern))
}
