package br.com.michellebrito.financefocus.passwordrecovery.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.michellebrito.financefocus.passwordrecovery.domain.PasswordRecoveryRepository

class PasswordRecoveryViewModel(private val repository: PasswordRecoveryRepository) : ViewModel() {
    fun sendPasswordRecovery(email: String) {
        repository.sendEmailToken(email) {
            Log.i("RECUPERACAO COM SUCESSO:", it.toString())
        }
    }
}
