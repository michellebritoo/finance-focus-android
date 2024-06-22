package br.com.michellebrito.financefocus.passwordrecovery.data

import br.com.michellebrito.financefocus.passwordrecovery.domain.PasswordRecoveryRepository
import com.google.firebase.auth.FirebaseAuth

class PasswordRepositoryImpl: PasswordRecoveryRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun sendEmailToken(email: String, callback: (Boolean) -> Unit) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }
}