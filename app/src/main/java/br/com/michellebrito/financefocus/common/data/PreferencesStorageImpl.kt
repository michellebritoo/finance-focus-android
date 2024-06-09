package br.com.michellebrito.financefocus.common.data

import android.content.Context
import android.content.SharedPreferences
import br.com.michellebrito.financefocus.common.domain.PreferenceStorage

class PreferencesStorageImpl(context: Context) : PreferenceStorage {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        FINANCE_FOCUS_STORAGE, Context.MODE_PRIVATE
    )

    override fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, null) ?: ""
    }

    private companion object {
        const val FINANCE_FOCUS_STORAGE = "finance_focus_storage"
    }
}
