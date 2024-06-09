package br.com.michellebrito.financefocus.common.domain

interface PreferenceStorage {
    fun putBoolean(key: String, value: Boolean)
    fun getBoolean(key: String): Boolean
    fun putString(key: String, value: String)
    fun getString(key: String): String
}