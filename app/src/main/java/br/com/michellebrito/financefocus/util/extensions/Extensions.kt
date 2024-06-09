package br.com.michellebrito.financefocus.util.extensions

fun String?.orEmpty(): String {
    return this ?: ""
}

fun String?.isEmptyOrBlank(): Boolean {
    return this?.isEmpty() == true || this?.isBlank() == true
}
