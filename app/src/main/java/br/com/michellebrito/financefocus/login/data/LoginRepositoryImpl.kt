package br.com.michellebrito.financefocus.login.data

import br.com.michellebrito.financefocus.login.domain.LoginRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginRepositoryImpl : LoginRepository {
    private var auth: FirebaseAuth = Firebase.auth

    override fun authWithEmailAndPassword(
        email: String,
        password: String,
        callback: (Boolean) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }
}
